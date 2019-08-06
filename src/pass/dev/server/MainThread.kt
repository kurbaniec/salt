package pass.dev.server

import java.net.ServerSocket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

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