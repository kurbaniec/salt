package pass.salt.modules.server

import java.io.*
import java.net.Socket
import java.util.*

class ServerWorkerThread(val socket: Socket): Runnable {
    val inp = BufferedReader(InputStreamReader(socket.getInputStream()))
    val out = PrintWriter(socket.getOutputStream())
    val data = BufferedOutputStream(socket.getOutputStream())

    override fun run() {
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource("web/hello.html")
        val file = File(resource.file)
        val fileLength = file.length().toInt()
        val content = getContentType("web/hello.html")

        val fileData = readFileData(file, fileLength)

        // send HTTP Headers
        out.println("HTTP/1.1 200 OK")
        out.println("Server: Java HTTP Server from SSaurel : 1.0")
        out.println("Date: " + Date())
        out.println("Content-type: $content")
        out.println("Content-length: $fileLength")
        out.println() // blank line between headers and content, very important !
        out.flush() // flush character output stream buffer

        data.write(fileData, 0, fileLength)
        data.flush()
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
}