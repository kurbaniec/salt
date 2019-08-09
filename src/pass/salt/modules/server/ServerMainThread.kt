package pass.salt.modules.server

import pass.salt.modules.SaltThreadPool
import pass.salt.server.HandlerThread
import java.io.*
import java.net.ServerSocket
import kotlin.reflect.KFunction
import java.security.KeyStore
import javax.net.ssl.*


// TODO 08.08 connect ServerMainThread with Get Annotation
class ServerMainThread(
    val executor: SaltThreadPool,
    val socket: SSLServerSocket
): Runnable {
    val mapping = mutableMapOf<String, MutableMap<String, Pair<Any, KFunction<*>>>>()
    var listening = true

    init {
        val getMapping = mutableMapOf<String, Pair<Any, KFunction<*>>>()
        val postMapping = mutableMapOf<String, Pair<Any, KFunction<*>>>()
        mapping["get"] = getMapping
        mapping["post"] = postMapping
    }

    override fun run() {
        while (listening) {
            executor.submit(ServerWorkerThread(socket.accept() as SSLSocket, this))
        }
    }

    fun addGetMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["get"]?.set(path, call)
    }

    fun addPostMapping(path: String, call: Pair<Any, KFunction<*>>) {
        mapping["post"]?.set(path, call)
    }

    fun getGetMapping(path: String): String? {
        //return mapping["get"]?.get(path)
        return getMapping(path, "get")
    }

    fun getPostMapping(path: String): String? {
        //return mapping["post"]?.get(path)
        return getMapping(path, "post")
    }

    fun getMapping(path: String, method: String): String? {
        val pair = mapping[method]?.get(path)
        if (pair != null) {
            val file = pair.call()
            if (file != null && file is String) {
                return if (file.endsWith(".html")) file
                else "$file.html"
            }
        }
        return null
    }

    private fun Pair<Any, KFunction<*>>.call(vararg args: Any?): Any? {
        val (instance, func) = this
        return func.call(instance, *args)
    }


}