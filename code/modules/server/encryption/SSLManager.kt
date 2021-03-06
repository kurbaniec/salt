package pass.salt.code.modules.server.encryption

import sun.security.tools.keytool.CertAndKeyGen
import sun.security.x509.X500Name
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.KeyStore
import java.security.cert.X509Certificate
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import pass.salt.code.modules.server.ServerMainThread

/**
 * Contains all SSL-Configuration for the HTTPS-[ServerMainThread].
 */
class SSLManager {
    companion object {
        /**
         * Create the and initialize the SSLContext
         */
        fun createSSLContext(password: String, file: String): SSLContext? {
            try {
                val keyStore = KeyStore.getInstance("JKS")
                keyStore.load(FileInputStream(file), password.toCharArray())
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

        /**
         * Create keystore.
         */
        fun createKeyStore(password: String) {
            try {
                val keyStoreFile = File(
                    Thread.currentThread().contextClassLoader.getResource("test.jks")!!.toURI())

                var keyStore = KeyStore.getInstance("JKS")
                keyStore.load(null, null)

                keyStore.store(FileOutputStream(keyStoreFile), password.toCharArray())

                keyStore = KeyStore.getInstance("JKS")
                keyStore.load(FileInputStream(keyStoreFile), password.toCharArray())

                var gen = CertAndKeyGen("RSA", "SHA1WithRSA")
                gen.generate(1024)

                val key = gen.privateKey
                var cert = gen.getSelfCertificate(X500Name("CN=ROOT"), 365.toLong() * 24 * 3600)

                val chain = arrayOfNulls<X509Certificate>(1)
                chain[0] = cert

                keyStore.setKeyEntry("mykey", key, password.toCharArray(), chain)

                keyStore.store(FileOutputStream(keyStoreFile), password.toCharArray())

                keyStore = KeyStore.getInstance("JKS")
                keyStore.load(FileInputStream(keyStoreFile), password.toCharArray())

                gen = CertAndKeyGen("RSA", "SHA1WithRSA")
                gen.generate(1024)

                cert = gen.getSelfCertificate(X500Name("CN=SINGLE_CERTIFICATE"), 365.toLong() * 24 * 3600)

                keyStore.setCertificateEntry("single_cert", cert)

                keyStore.store(FileOutputStream(keyStoreFile), password.toCharArray())

                //println(cert)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

    }
}