package pass.salt.modules.server

import java.io.*
import java.net.Socket
import java.util.*
import java.util.StringTokenizer
import pass.salt.loader.config.Config
import java.io.BufferedOutputStream
import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import java.net.ServerSocket
import javax.net.ssl.SSLSocket

class ServerWorkerThread<P: ServerSocket, S: Socket>(
        val socket: S,
        val server: ServerMainThread<P>,
        val config: Config
): Runnable {
    val inp = BufferedReader(InputStreamReader(socket.getInputStream()))
    val out = PrintWriter(socket.getOutputStream())
    val data = BufferedOutputStream(socket.getOutputStream())
    val WEB_ROOT: File
    val METHOD_NOT_SUPPORTED = "not_supported.html"
    val FILE_NOT_FOUND = "404.html"
    var listening = true

    init {
        val path = System.getProperty("user.dir")
        WEB_ROOT = File(path, "res\\web")
    }

    data class Request(val method: String, val path: String, val params: Map<String, String>)

    override fun run() {
        if (socket is SSLSocket) {
            socket.enabledCipherSuites = socket.supportedCipherSuites;
            socket.startHandshake()
        } else if (config.findObjectAttribute("server", "redirect") as Boolean) {
            val input = inp.readLine()
            val parse = StringTokenizer(input)
            parse.nextToken()
            val path = parse.nextToken().toLowerCase()
            redirect(path)
            return
        }
        // TODO kill handler after a time - Terminate Service?
        while (listening) {
            /**
            // get first line of the request from the client
            val input = inp.readLine()
            val header = readHeader()
            //val test = inp.readText()
            // we parse the request with a string tokenizer
            val parse = StringTokenizer(input)
            val method = parse.nextToken().toUpperCase() // we get the HTTP method of the client
            // we get file requested
            val path = parse.nextToken().toLowerCase()*/
            val request = readRequest()
            // Unsupported mapping
            if (request.method != "GET" && request.method != "POST" && request.method != "HEAD") {
                val file = File(WEB_ROOT, METHOD_NOT_SUPPORTED)
                val fileLength = file.length().toInt()
                val contentMimeType = "text/html"
                //read content to return to client
                val fileData = readFileData(file, fileLength)
                sendHelper("HTTP/1.1 501 Not Implemented",
                        "Server: SaltApplication",
                        contentMimeType, fileLength, fileData)
            } else {
                val mapping = when (request.method) {
                    "GET" -> server.getGetMapping(request.path)
                    "POST" -> server.getPostMapping(request.path)
                    else -> null
                }
                if (mapping != null) {
                    mapping.addParams(request.params)
                    if ((request.method == "GET" || request.method == "POST") && mapping != null) {
                        val fileName = mapping.call()
                        val file = File(WEB_ROOT, fileName)
                        val fileLength = file.length().toInt()
                        val content = getContentType(fileName)
                        val fileData = readFileData(file, fileLength)
                        sendHelper("HTTP/1.1 200 OK",
                                "Server: SaltApplication",
                                content, fileLength, fileData)
                    } else fileNotFound()
                }
            }
        }
    }

    private fun sendHelper(header: String, server: String, contentType: String, fileLength: Int, fileData: ByteArray) {
        out.println(header)
        out.println(server)
        out.println("Date: ${Date()}")
        out.println("Content-type: $contentType")
        out.println("Content-length: $fileLength")
        out.println()
        out.flush()
        data.write(fileData, 0, fileLength)
        data.flush()
        // TODO close connection?
        //out.close()
        //data.close()
        //socket.close()
    }

    // return supported MIME Types
    private fun getContentType(fileRequested: String): String {
        return if (fileRequested.endsWith(".htm") || fileRequested.endsWith(".html"))
            "text/html"
        else
            "text/plain"
    }

    @Throws(IOException::class)
    private fun readFileData(file: File, fileLength: Int): ByteArray {
        var fileIn: FileInputStream? = null
        val fileData = ByteArray(fileLength)

        try {
            fileIn = FileInputStream(file)
            fileIn.read(fileData)
        } finally {
            fileIn?.close()
        }

        return fileData
    }

    private fun fileNotFound() {
        val file = File(WEB_ROOT, FILE_NOT_FOUND)
        val fileLength = file.length().toInt()
        val content = "text/html"
        val fileData = readFileData(file, fileLength)
        sendHelper("HTTP/1.1 404 File Not Found",
                "Server: SaltApplication",
                content, fileLength, fileData)
    }

    private fun redirect(path: String) {
        val ip = config.findObjectAttribute("server", "ip_address") as String
        val port = config.findObjectAttribute("server", "https_port").toString()
        out.println("HTTP/1.1 302 Found")
        out.println("Server: SaltApplication")
        out.println("Date: ${Date()}")
        out.println("Content-type: text/plain")
        out.println("Location: https://$ip:$port$path")
        out.println("Connection: Close")
        out.println()
        out.flush()
        shutdown()
    }

    fun shutdown() {
        listening = false
        inp.close()
        out.close()
        data.close()
        socket.close()
    }

    private fun readRequest(): Request {
        val req = inp.readLine()
        val reqLs = req.split(" ")
        val method = reqLs[0].toUpperCase()
        val pathParam = reqLs[1].split(Regex.fromLiteral("?"), 2)
        val path = pathParam[0].toLowerCase()
        var params = mapOf<String, String>()
        if (pathParam.size > 1) {
            val parLs = pathParam[1].split("&")
            params = parLs.map {
                val tmp = it.split("=")
                tmp[0] to tmp[1]
            }.toMap()
        }
        return Request(method, path, params)
    }

    private fun readHeader(): MutableMap<String, String> {
        val header = mutableMapOf<String, String>()
        var adder = ""
        do {
            adder = inp.readLine()
            if (adder != "") {
                val data = adder.split(Regex.fromLiteral(":"), 2)
                header[data[0]] = data[1]
            }

        } while (adder != "")
        return header
    }

}