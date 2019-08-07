package pass.salt.modules.server

import pass.salt.modules.SaltThreadPool
import pass.salt.server.HandlerThread
import java.io.BufferedOutputStream
import java.io.BufferedReader
import java.io.PrintWriter
import java.net.ServerSocket

class ServerMainThread(
        val executor: SaltThreadPool,
        val socket: ServerSocket
): Runnable {
    var listening = true

    override fun run() {
        while (listening)
            executor.submit(ServerWorkerThread(socket.accept()))
    }
}