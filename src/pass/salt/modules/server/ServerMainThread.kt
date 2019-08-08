package pass.salt.modules.server

import pass.salt.modules.SaltThreadPool
import pass.salt.server.HandlerThread
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.ServerSocket
import kotlin.reflect.KFunction

// TODO 08.08 connect ServerMainThread with Get Annotation
class ServerMainThread(
    val executor: SaltThreadPool,
    val socket: ServerSocket
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
        while (listening)
            executor.submit(ServerWorkerThread(socket.accept(), mapping))
    }
}