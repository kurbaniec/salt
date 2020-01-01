package pass.salt.code.modules.server

import pass.salt.code.modules.SaltThreadPool
import java.net.ServerSocket
import kotlin.reflect.KFunction
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.server.encryption.SSLManager
import pass.salt.code.modules.server.mapping.Mapping
import pass.salt.code.modules.server.security.SaltSecurity
import javax.net.ssl.SSLServerSocket
import javax.net.ssl.SSLSocket

/**
 * Main server thread that listens for http- or https client connections and spawns a [ServerWorkerThread]
 * for every new connection request.
 */
class ServerMainThread<P: ServerSocket>(
        val executor: SaltThreadPool,
        val serverSocket: P,
        val mapping: MutableMap<String, Mapping>,
        val config: Config,
        val security: Pair<Boolean, SaltSecurity?>
): Runnable {
    var listening = true

    /**
     * Start server main thread.
     * If it is the https server, an [SSLServerSocket] is used,
     * which uses the configured keystore from the [SSLManager].
     */
    override fun run() {
        if (serverSocket is SSLServerSocket) {
            val password = config.findObjectAttribute("keystore", "password") as String
            SSLManager.createKeyStore(password)
            while (listening) {
                executor.submit(ServerWorkerThread(serverSocket.accept() as SSLSocket, this, config, security))
            }
        }
        else {
            while (listening) {
                executor.submit(ServerWorkerThread(serverSocket.accept(), this, config, security))
            }
        }
    }

    /**
     * Add mapping, a function call, when a given get request is received.
     */
    fun addGetMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["get"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    /**
     * Add mapping, a function call, when a given post request is received.
     */
    fun addPostMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["post"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    /**
     * Return get mapping for a request path.
     */
    fun getGetMapping(path: String): Mapping.MappingFunction? {
        return getMapping(path, "get")
    }

    /**
     * Return post mapping for a request path.
     */
    fun getPostMapping(path: String): Mapping.MappingFunction? {
        return getMapping(path, "post")
    }

    /**
     * Return either a get or post mapping for a request path.
     */
    private fun getMapping(path: String, method: String): Mapping.MappingFunction? {
        return mapping[method]?.getMapping(path)
    }
}