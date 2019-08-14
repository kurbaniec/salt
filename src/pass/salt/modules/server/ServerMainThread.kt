package pass.salt.modules.server

import pass.salt.annotations.WebSecurity
import pass.salt.modules.SaltThreadPool
import java.net.ServerSocket
import kotlin.reflect.KFunction
import pass.salt.loader.config.Config
import pass.salt.modules.server.encryption.SSLManager
import pass.salt.modules.server.mapping.Mapping
import pass.salt.modules.server.security.SaltSecurity
import javax.net.ssl.SSLServerSocket
import javax.net.ssl.SSLSocket

class ServerMainThread<P: ServerSocket>(
        val executor: SaltThreadPool,
        val serverSocket: P,
        //val mapping: MutableMap<String, MutableMap<String, Pair<Any, KFunction<*>>>>,
        val mapping: MutableMap<String, Mapping>,
        val config: Config,
        val security: Pair<Boolean, SaltSecurity?>
): Runnable {
    //val mapping = mutableMapOf<String, MutableMap<String, Pair<Any, KFunction<*>>>>()
    var listening = true

    /**
    init {
        val getMapping = mutableMapOf<String, Pair<Any, KFunction<*>>>()
        val postMapping = mutableMapOf<String, Pair<Any, KFunction<*>>>()
        mapping["get"] = getMapping
        mapping["post"] = postMapping
    }*/

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

    fun addGetMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["get"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    fun addPostMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["post"]?.addMapping(path, Mapping.MappingFunction(path, call.first, call.second))
    }

    fun getGetMapping(path: String): Mapping.MappingFunction? {
        //return mapping["get"]?.get(path)
        return getMapping(path, "get")
    }

    fun getPostMapping(path: String): Mapping.MappingFunction? {
        //return mapping["post"]?.get(path)
        return getMapping(path, "post")
    }

    fun getMapping(path: String, method: String): Mapping.MappingFunction? {
        return mapping[method]?.getMapping(path)
        //val pair = mapping[method]?.get(path)
        /**val pair = mapping[method]?.getMapping(path)
        if (pair != null) {
            val file = pair.call()
            if (file != null && file is String) {
                return if (file.endsWith(".html")) file
                else "$file.html"
            }
        }
        return null*/
    }

    private fun Pair<Any, KFunction<*>>.call(vararg args: Any?): Any? {
        val (instance, func) = this
        return func.call(instance, *args)
    }


}