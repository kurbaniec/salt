package pass.dev.server

import sun.applet.Main
import java.lang.Exception
import java.net.ServerSocket

class Server() {
    var server: ServerSocket? = null
    var main: MainThread? = null

    init {
        try {
            server = ServerSocket(8080)
            if (server != null) {
                main = MainThread(server!!)
                Thread(main).run()
            }
        }
        catch (ex: Exception) {
            println("boom")
        }

    }
}