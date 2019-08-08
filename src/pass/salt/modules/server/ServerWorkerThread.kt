package pass.salt.modules.server

import java.io.*
import java.net.Socket
import java.util.*
import kotlin.reflect.KFunction
import java.util.StringTokenizer
import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.io.BufferedOutputStream
import java.io.PrintWriter
import java.io.InputStreamReader
import java.io.BufferedReader



class ServerWorkerThread(
        val socket: Socket,
        val server: ServerMainThread
): Runnable {
    val inp = BufferedReader(InputStreamReader(socket.getInputStream()))
    var out = PrintWriter(socket.getOutputStream())
    val data = BufferedOutputStream(socket.getOutputStream())
    val WEB_ROOT: File
    val METHOD_NOT_SUPPORTED = "not_supported.html"
    val FILE_NOT_FOUND = "404.html"
    val listening =  true

    init {
        /**
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource("web")*/
        val path = System.getProperty("user.dir")
        // TODO WEB_ROOT
        //WEB_ROOT = File(resource.file)
        WEB_ROOT = File(path, "res\\web")
    }

    override fun run() {
        while (listening) {
            // get first line of the request from the client
            val input = inp.readLine()
            // we parse the request with a string tokenizer
            val parse = StringTokenizer(input)
            val method = parse.nextToken().toUpperCase() // we get the HTTP method of the client
            // we get file requested
            val path = parse.nextToken().toLowerCase()

            // Unsupported mapping
            if (method != "GET" && method != "POST" && method != "HEAD") {
                val file = File(WEB_ROOT, METHOD_NOT_SUPPORTED)
                val fileLength = file.length().toInt()
                val contentMimeType = "text/html"
                //read content to return to client
                val fileData = readFileData(file, fileLength)
                send("HTTP/1.1 501 Not Implemented",
                        "Server: SaltApplication",
                        contentMimeType, fileLength, fileData)
            } else {
                val fileEnd = when (method) {
                    "GET" -> server.getGetMapping(path)
                    "POST" -> server.getPostMapping(path)
                    else -> null
                }

                if ((method == "GET" || method == "POST") && fileEnd != null) {
                    val file = File(WEB_ROOT, fileEnd)
                    val fileLength = file.length().toInt()
                    val content = getContentType(fileEnd)
                    val fileData = readFileData(file, fileLength)
                    send("HTTP/1.1 200 OK",
                            "Server: SaltApplication",
                            content, fileLength, fileData)
                } else fileNotFound()
            }
        }
    }

    private fun send(header: String, server: String, contentType: String, fileLength: Int, fileData: ByteArray) {
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
        send("HTTP/1.1 404 File Not Found",
                "Server: SaltApplication",
                content, fileLength, fileData)
    }


}