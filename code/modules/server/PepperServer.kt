package pass.salt.code.modules.server

import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor
import pass.salt.code.modules.SaltThreadPool
import pass.salt.code.modules.server.encryption.SSLManager
import pass.salt.code.modules.server.mapping.HTTPMethod
import pass.salt.code.modules.server.mapping.Mapping
import pass.salt.code.modules.server.security.SaltSecurity
import java.io.File
import java.net.ServerSocket
import javax.net.ssl.SSLServerSocket
import java.util.logging.Logger
import kotlin.reflect.KFunction


class PepperServer(
        val config: Config,
        val container: Container
): SaltProcessor {
    val log = Logger.getLogger("SaltLogger")
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
            log.fine("Starting http-port...")
            val port = config.findObjectAttribute("server", "http_port") as Int
            val socket = ServerSocket(port)
            serverHttp = ServerMainThread(executor, socket, mapping, config, security)
            container.addElement("serverMainThreadHttp", serverHttp)
            executor.submit(serverHttp)
            log.fine("...done")
        }
        if (config.findObjectAttribute("server", "https") as Boolean) {
            log.fine("Starting https-port...")
            val port = config.findObjectAttribute("server", "https_port") as Int
            val password = config.findObjectAttribute("keystore", "password") as String
            val keyStoreFile = File(
                Thread.currentThread().contextClassLoader.getResource("test.jks")!!.toURI())
            val sslContext = if (config.findObjectAttribute("keystore", "generate")) {
                SSLManager.createKeyStore(password)
                SSLManager.createSSLContext(password, keyStoreFile.absolutePath)
            }
            else {
                val file = config.findObjectAttribute<String>("keystore", "file")
                SSLManager.createSSLContext(password, "res/$file")
            }
            val sslServerSocketFactory = sslContext?.serverSocketFactory
            val socket = sslServerSocketFactory?.createServerSocket(port) as SSLServerSocket
            serverHttps = ServerMainThread(executor, socket, mapping, config, security)
            container.addElement("serverMainThreadHttps", serverHttps)
            executor.submit(serverHttps)
            log.fine("...done")
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