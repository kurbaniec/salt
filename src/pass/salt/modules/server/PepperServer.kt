package pass.salt.modules.server

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool
import java.io.FileInputStream
import java.net.ServerSocket
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLServerSocket
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.SSLServerSocketFactory
import java.io.FileOutputStream
import sun.security.x509.X500Name
import sun.security.tools.keytool.CertAndKeyGen
import java.security.cert.X509Certificate


class PepperServer(
        val config: Config,
        val container: Container
): SaltProcessor {
    //lateinit var socket: SSLServerSocket
    lateinit var socket: ServerSocket
    lateinit var server: ServerMainThread

    override fun process(className: String) {
        val executor = container.getElement("saltThreadPool") as SaltThreadPool
        val port = config.findObjectAttribute("server", "port") as Int
        /**
         * val password = config.findObjectAttribute("keystore", "password") as String
        //socket = ServerSocket(port)
        this.createKeyStore()
        val sslContext = this.createSSLContext()
        // Create server socket factory
        val sslServerSocketFactory = sslContext?.serverSocketFactory
        // Create server socket
        val socket = sslServerSocketFactory?.createServerSocket(port) as SSLServerSocket
        */
        socket = ServerSocket(port)
        server = ServerMainThread(executor, socket, config)
        container.addElement("serverMainThread", server)
        executor.submit(server)
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}