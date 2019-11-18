import com.gargoylesoftware.htmlunit.BrowserVersion.CHROME
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient
import org.junit.BeforeClass
import pass.salt.code.SaltApplication
import java.io.FileNotFoundException
import java.net.MalformedURLException
import java.net.URL
import java.security.cert.X509Certificate
import javax.net.ssl.*
import kotlin.test.Test
import kotlin.test.assertEquals


/**
 * Test Salt-Framework with default configuration.
 *
 * @author Kacper Urbaniec
 * @version 2019-11-18
 */
class SaltDefaultTest {
    companion object {

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
            val app = SaltApplication()
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
     * Simple curl method.
     */
    private fun curl(url: String): String {
        return URL(url).readText()
    }

    @Test
    fun testGetHomePage() {
        val actual = curl("https://localhost:8080/")
        assert(actual.contains("Welcome to Salt!"))
    }

    @Test(expected = FileNotFoundException::class)
    // source: https://stackoverflow.com/a/5379288/12347616
    fun testGetUnavailablePage() {
        val actual = curl("https://localhost:8080/doesnotexist")
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

    // TODO Annotations, HTML-Parsing Engine, post/get requests
    
}