package pass.salt.modules.server

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool
import java.net.ServerSocket

class PepperServer(
        val config: Config,
        val container: Container
): SaltProcessor {
    lateinit var socket: ServerSocket
    lateinit var server: ServerMainThread

    override fun process(className: String) {
        val executor = container.getElement("saltThreadPool") as SaltThreadPool
        val port = config.findObjectAttribute("server", "port") as Int
        socket = ServerSocket(port)
        server = ServerMainThread(executor, socket)
        container.addElement("serverMainThread", server)
        executor.submit(server)
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}