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
    lateinit var socket: SSLServerSocket
    lateinit var server: ServerMainThread

    override fun process(className: String) {
        val executor = container.getElement("saltThreadPool") as SaltThreadPool
        val port = config.findObjectAttribute("server", "port") as Int
        //socket = ServerSocket(port)
        this.createKeyStore()
        val sslContext = this.createSSLContext()
        // Create server socket factory
        val sslServerSocketFactory = sslContext?.serverSocketFactory
        // Create server socket
        val socket = sslServerSocketFactory?.createServerSocket(port) as SSLServerSocket

        server = ServerMainThread(executor, socket)
        container.addElement("serverMainThread", server)
        executor.submit(server)
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // Create the and initialize the SSLContext
    private fun createSSLContext(): SSLContext? {
        val password = config.findObjectAttribute("keystore", "password") as String
        try {
            val keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream("test.jks"), password.toCharArray())
            // Create key manager
            val keyManagerFactory = KeyManagerFactory.getInstance("SunX509")
            keyManagerFactory.init(keyStore, password.toCharArray())
            val km = keyManagerFactory.getKeyManagers()
            // Create trust manager
            val trustManagerFactory = TrustManagerFactory.getInstance("SunX509")
            trustManagerFactory.init(keyStore)
            val tm = trustManagerFactory.getTrustManagers()
            // Initialize SSLContext
            val sslContext = SSLContext.getInstance("TLSv1")
            sslContext.init(km, tm, null)

            return sslContext
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null
    }

    fun createKeyStore() {
        val password = config.findObjectAttribute("keystore", "password") as String
        try {
            var keyStore = KeyStore.getInstance("JKS")
            keyStore.load(null, null)

            keyStore.store(FileOutputStream("test.jks"), password.toCharArray())


            keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream("test.jks"), password.toCharArray())

            var gen = CertAndKeyGen("RSA", "SHA1WithRSA")
            gen.generate(1024)

            val key = gen.privateKey
            var cert = gen.getSelfCertificate(X500Name("CN=ROOT"), 365.toLong() * 24 * 3600)

            val chain = arrayOfNulls<X509Certificate>(1)
            chain[0] = cert

            keyStore.setKeyEntry("mykey", key, password.toCharArray(), chain)

            keyStore.store(FileOutputStream("test.jks"), password.toCharArray())

            keyStore = KeyStore.getInstance("JKS")
            keyStore.load(FileInputStream("test.jks"), password.toCharArray())

            gen = CertAndKeyGen("RSA", "SHA1WithRSA")
            gen.generate(1024)

            cert = gen.getSelfCertificate(X500Name("CN=SINGLE_CERTIFICATE"), 365.toLong() * 24 * 3600)

            keyStore.setCertificateEntry("single_cert", cert)

            keyStore.store(FileOutputStream("test.jks"), password.toCharArray())

            println(cert)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }
}