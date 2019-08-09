package pass.salt.modules.server

import pass.salt.modules.SaltThreadPool
import java.net.ServerSocket
import kotlin.reflect.KFunction
import javax.net.ssl.*
import pass.salt.loader.config.Config
import pass.salt.modules.server.encryption.SSLManager
import java.io.InputStreamReader
import java.io.BufferedReader




// TODO 08.08 connect ServerMainThread with Get Annotation
class ServerMainThread(
        val executor: SaltThreadPool,
    //val socket: SSLServerSocket
        val serverSocket: ServerSocket,
        val config: Config
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
        val password = config.findObjectAttribute("keystore", "password") as String
        SSLManager.createKeyStore(password)
        while (listening) {
            executor.submit(ServerWorkerThread(serverSocket.accept(), this, config))
        }
        /**val sslContext = SSLManager.createSSLContext(password)
        val redirect = config.findObjectAttribute("server", "redirect") as Boolean
        while (listening) {
            val socket = serverSocket.accept()
            if (redirect) {
                val inp = BufferedReader(InputStreamReader(socket.getInputStream()))
                val line = inp.readLine()
                // HTTPS:
                if (!Character.isLetter(line[0]) && !Character.isLetter(line[1])) {
                    println("https")
                    // Create server socket factory
                    val sslServerSocketFactory = sslContext?.socketFactory
                    // Create server socket
                    val sslSocket = sslServerSocketFactory?.createSocket(
                            socket, null, socket.port, false) as SSLSocket
                    sslSocket.useClientMode = false;
                    executor.submit(ServerWorkerThread(sslSocket, this))
                }
                // HTTP:
                else {
                    println("http")
                    executor.submit(ServerWorkerThread(socket, this))
                }
                /**while (line != "") {
                    println(line)
                    line = inp.readLine()
                }*/
                //inp.close()
            }

            print("Baum")
            //executor.submit(ServerWorkerThread(socket.accept() as SSLSocket, this))
        }*/
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