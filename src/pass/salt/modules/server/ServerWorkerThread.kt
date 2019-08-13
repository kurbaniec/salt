package pass.salt.modules.server

import com.sun.org.apache.xerces.internal.parsers.DOMParser
import java.io.*
import java.net.Socket
import java.util.*
import pass.salt.loader.config.Config
import pass.salt.modules.server.webparse.Model
import pass.salt.modules.server.webparse.Webparse
import java.io.BufferedOutputStream
import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader
import java.net.ServerSocket
import javax.net.ssl.SSLSocket
import java.lang.StringBuilder


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
            val request = readRequest()
            redirect(request.path)
            return
        }
        // TODO kill handler after a time - Terminate Service?
        while (listening) {
            val request = readRequest()
            val header = readHeader()
            // Unsupported mapping
            if (request.method != "GET" && request.method != "POST" && request.method != "HEAD") {
                val file = File(WEB_ROOT, METHOD_NOT_SUPPORTED)
                val fileLength = file.length().toInt()
                val contentMimeType = "text/html"
                //read content to return to client
                val fileData = readFileData(file, fileLength, null)
                sendHelper("HTTP/1.1 501 Not Implemented",
                        "Server: SaltApplication",
                        contentMimeType, fileData.second, fileData.first)
            } else {
                val mapping = when (request.method) {
                    "GET" -> server.getGetMapping(request.path)
                    "POST" -> server.getPostMapping(request.path)
                    else -> null
                }
                if ((request.method == "GET" || request.method == "POST") && mapping != null) {
                    mapping.addParams(request.params)
                    val model = mapping.model
                    val fileName = mapping.call()
                    val file = File(WEB_ROOT, fileName)
                    val fileLength = file.length().toInt()
                    val content = getContentType(fileName)
                    val fileData = readFileData(file, fileLength, model)
                    sendHelper("HTTP/1.1 200 OK",
                            "Server: SaltApplication",
                            content, fileData.second, fileData.first)
                } else fileNotFound()
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
    private fun readFileData(file: File, fileLength: Int, model: Model?): Pair<ByteArray, Int> {
        val raw = file.readText(Charsets.UTF_8)
        val lines = raw.split("\r\n").toMutableList()
        if (model != null) {
            Webparse.parse(lines, model)
        }
        val site = StringBuilder()
        for (l in lines) {
            site.append(l + "\r\n")
        }
        //val bytes = test.toByteArray(Charsets.UTF_8)
        val bytes = site.toString().toByteArray(Charsets.UTF_8)
        val length = bytes.size

        /**val file = file.useLines {
            (it).toList()
        }
        val baos = ByteArrayOutputStream()
        val out = DataOutputStream(baos)
        for (element in file) {
            out.writeUTF(element)
        }
        val bytes = baos.toByteArray()*/

        /**
        val fileLength = file.length().toInt()
        var fileIn: FileInputStream? = null
        val fileData = ByteArray(fileLength)
        try {
            fileIn = FileInputStream(file)
            fileIn.read(fileData)
        } finally {
            fileIn?.close()
        }*/

        return Pair(bytes, length)
    }

    private fun fileNotFound() {
        val file = File(WEB_ROOT, FILE_NOT_FOUND)
        val fileLength = file.length().toInt()
        val content = "text/html"
        val fileData = readFileData(file, fileLength, null)
        sendHelper("HTTP/1.1 404 File Not Found",
                "Server: SaltApplication",
                content, fileData.second, fileData.first)
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