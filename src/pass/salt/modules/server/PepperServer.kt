package pass.salt.modules.server

import pass.salt.annotations.WebSecurity
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool
import pass.salt.modules.server.encryption.SSLManager
import pass.salt.modules.server.mapping.HTTPMethod
import pass.salt.modules.server.mapping.Mapping
import pass.salt.modules.server.security.SaltSecurity
import java.io.FileInputStream
import java.net.ServerSocket
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLServerSocket
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.SSLServerSocketFactory
import java.io.FileOutputStream
import sun.security.x509.X500Name
import sun.security.tools.keytool.CertAndKeyGen
import java.security.cert.X509Certificate
import kotlin.reflect.KFunction


class PepperServer(
        val config: Config,
        val container: Container
): SaltProcessor {
    val mapping = mutableMapOf<String, Mapping>()
    lateinit var serverHttp: ServerMainThread<ServerSocket>
    lateinit var serverHttps: ServerMainThread<SSLServerSocket>
    var security: Pair<Boolean, SaltSecurity?> = Pair(false, null)

    init {
        val getMapping = Mapping(HTTPMethod.GET)
        val postMapping = Mapping(HTTPMethod.POST)
        mapping["get"] = getMapping
        mapping["post"] = postMapping
    }
    override fun process(className: String) {
        container.addElement("pepperServer", this)
        if (config.findObjectAttribute("security", "enable") as Boolean) {
            security = Pair(true, container.getElement("saltSecurity") as SaltSecurity)
        }
        val executor = container.getElement("saltThreadPool") as SaltThreadPool
        if (config.findObjectAttribute("server", "http") as Boolean) {
            val port = config.findObjectAttribute("server", "http_port") as Int
            val socket = ServerSocket(port)
            serverHttp = ServerMainThread(executor, socket, mapping, config, security)
            container.addElement("serverMainThreadHttp", serverHttp)
            executor.submit(serverHttp)
        }
        if (config.findObjectAttribute("server", "https") as Boolean) {
            val port = config.findObjectAttribute("server", "https_port") as Int
            val password = config.findObjectAttribute("keystore", "password") as String
            SSLManager.createKeyStore(password)
            val sslContext = SSLManager.createSSLContext(password)
            val sslServerSocketFactory = sslContext?.serverSocketFactory
            val socket = sslServerSocketFactory?.createServerSocket(port) as SSLServerSocket
            serverHttps = ServerMainThread(executor, socket, mapping, config, security)
            container.addElement("serverMainThreadHttps", serverHttps)
            executor.submit(serverHttps)
        }
    }

    fun addGetMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["get"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    fun addPostMapping(path: String, call: Pair<Any, KFunction<*>>) {
        //mapping["post"]?.set(path, call)
        mapping["post"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}