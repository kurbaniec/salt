package pass.salt.server

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainThread(server: ServerSocket): Runnable {
    val server = server
    var listening = true
    val executor: ExecutorService = Executors.newFixedThreadPool(4)

    override fun run() {
        while (listening) {
            executor.submit(HandlerThread(server.accept()))
        }
    }
}