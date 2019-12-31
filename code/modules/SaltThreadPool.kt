package pass.salt.code.modules

import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import kotlin.system.exitProcess

val logger = Logger.getLogger("webserver")

/**
 * Initializes executor service and thread pool for Salt.
 */
class SaltThreadPoolFactory(
        val config: Config,
        val container: Container
): SaltProcessor {
    lateinit var executor: SaltThreadPool

    /**
     * Initialize executor service.
     */
    override fun process(className: String) {
        val count = config.findObjectAttribute<Int>("threads", "count")
        // TODO more ThreadPool types
        executor = SaltThreadPool("FixedThreadPool", count)
        container.addElement("saltThreadPool", executor)
    }

    /**
     * Shutdown executor service.
     */
    override fun shutdown() {
        executor.shutdown()
    }
}

/**
 * Representation of an executor service that initializes a concrete one from a given type.
 */
class SaltThreadPool(
        private val type: String,
        private val threadCount: Int
) {
    private val executor: ExecutorService

    init {
        executor = when(type) {
            "FixedThreadPool" -> Executors.newFixedThreadPool(threadCount)
            "CachedThreadPool" -> Executors.newCachedThreadPool()
            else -> Executors.newFixedThreadPool(threadCount)
        }
    }

    /**
     * Submit new task to executor.
     */
    fun submit(task: Runnable) {
        executor.submit(task)
    }

    /**
     * Shutdown executor service.
     */
    fun shutdown() {
        executor.shutdownNow()
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            logger.warning("Exiting with forced shutdown")
            exitProcess(0)
        }
        logger.info("Exiting normally")
    }


}