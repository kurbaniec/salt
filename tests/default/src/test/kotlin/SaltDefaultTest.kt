import com.gargoylesoftware.htmlunit.BrowserVersion.CHROME
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient
import def.dev.AutowiredTest
import def.dev.Controller
import org.junit.BeforeClass
import pass.salt.code.SaltApplication
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.server.PepperServer
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


/**
 * Test Salt-Framework with default configuration.
 *
 * @author Kacper Urbaniec
 * @version 2019-11-18
 */
class SaltDefaultTest {
    companion object {

        private lateinit var app: SaltApplication
        private lateinit var container: Container

        /**
         * Initialize the Salt framework.
         * Disable all https security for testing.
         */
        @BeforeClass
        @JvmStatic
        fun initSalt() {
            // Disable ssl security
            // source: https://nakov.com/blog/2009/07/16/disable-certificate-validation-in-java-ssl-connections/
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
                    return null
                }
                override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
            })
            val sc = SSLContext.getInstance("SSL")
            sc.init(null, trustAllCerts, java.security.SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)
            val allHostsValid = HostnameVerifier { hostname, session -> true }
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid)
            // Init salt and wait a bit for initialization
            app = SaltApplication()
            container = app.loader.container
            Thread.sleep(5000)
        }

        /**
         * Creates a WebClient-Object that is needed to simulate a "GUI-Less browser"
         * for testing various things.
         */
        @Throws(MalformedURLException::class)
        private fun createWebClient(): WebClient {
            val webClient = WebClient(CHROME)
            val options = webClient.options
            options.isJavaScriptEnabled = true
            options.isRedirectEnabled = true
            options.isUseInsecureSSL = true
            return webClient
        }
    }

    /**
     * Simple get curl method.
     */
    private fun curlGet(url: String): String {
        return URL(url).readText()
    }

    /**
     * Simple post curl method.
     */
    private fun curlPost(url: String): String {
        val con = URL(url)
        var response = ""
        with(con.openConnection() as HttpURLConnection) {
            requestMethod  = "POST"
            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    response += line
                }
            }
        }
        return response
    }

    @Test
    fun testInit() {
        assertNotNull(container.getElement("config"))
        assertNotNull(container.getElement("saltThreadPool"))
        assertNotNull(container.getElement("pepperServer"))
        assertNotNull(container.getElement("serverMainThreadHttp"))
        assertNotNull(container.getElement("serverMainThreadHttps"))
    }

    @Test
    fun testConfig() {
        val config = container.getElement("config") as Config
        assertEquals("localhost", config.findObjectAttribute("server", "ip_address"))
        assertEquals(true, config.findObjectAttribute("server", "http"))
        assertEquals(true, config.findObjectAttribute("server", "https"))
        assertEquals(8079, config.findObjectAttribute("server", "http_port"))
        assertEquals(8080, config.findObjectAttribute("server", "https_port"))
        assertEquals(true, config.findObjectAttribute("server", "redirect"))
        assertEquals("https", config.findObjectAttribute("server", "redirect_protocol"))
    }

    @Test
    fun testControllerInit() {
        val controller = container.getElement("controller") as Controller
        assertNotNull(controller)
        assertEquals("index", controller.index())
        val server = container.getElement("pepperServer") as PepperServer
        assertEquals("/", server.mapping["get"]!!.getMapping("/")!!.path)
        assertEquals("/template", server.mapping["get"]!!.getMapping("/template")!!.path)
    }

    @Test
    fun testAutowired() {
        val test = container.getElement("autowiredTest")
        assertNotNull(test)
        assertEquals("Test", (test as AutowiredTest).value)
    }

    @Test
    fun testGetHomePage() {
        val actual = curlGet("https://localhost:8080/")
        assert(actual.contains("Welcome to Salt!"))
    }
    
    @Test(expected = FileNotFoundException::class)
    // source: https://stackoverflow.com/a/5379288/12347616
    fun testGetUnavailablePage() {
        val actual = curlGet("https://localhost:8080/doesnotexist")
    }

    @Test
    fun testRedirectFromHttpToHttps() {
        // Create WebClient
        val webClient = createWebClient()
        val originalURL = "http://localhost:8079/"
        // Visit page to test redirection, save browser history count
        webClient.getPage<Page>(originalURL)
        // Wait 6 seconds, so that redirection will be invoked (through JavaScript
        // after 5 seconds)
        Thread.sleep(3000)
        // Get current browser history count and current url
        val laterHistory = webClient.currentWindow.history.length
        val redURL = webClient.currentWindow.history.getUrl(laterHistory - 1).toString()
        // Check if redirection has happened
        assertEquals("https://localhost:8080/", redURL)
    }

    @Test
    fun testPostRequest() {
        val actual = curlPost("https://localhost:8080/api")
        assertEquals("Hi&Salt", actual)
    }

    @Test
    fun testTemplateEngine() {
        val actual = curlGet("https://localhost:8080/template")
        assert(actual.contains("Hello, Salt"))
        assert(actual.contains("user 0"))
        assert(actual.contains("user 1"))
    }
    
}