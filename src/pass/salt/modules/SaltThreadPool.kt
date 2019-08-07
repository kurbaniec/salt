package pass.salt.modules

import pass.salt.container.Container
import pass.salt.exceptions.ConfigException
import pass.salt.loader.config.Config
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import kotlin.system.exitProcess

val logger = Logger.getLogger("webserver")

class SaltThreadPoolFactory(
        val config: Config,
        val container: Container
): SaltProcessor {
    override fun process(className: String) {
        val configCount = config.findObjectAttribute("threads", "count")
        val count: Int
        count = if (configCount is Int) {
            configCount
        }
        else if (configCount is String) {
            val res = configCount.toIntOrNull()
            if (res is Int) {
                res
            } else throw ConfigException("Could not parse [threads.count] in config.toml")
        }
        else throw ConfigException("Could not parse [threads.count] in config.toml")
        // TODO more ThreadPool types
        container.addElement("saltThreadPool", SaltThreadPool("FixedThreadPool", count))
    }
}

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

    fun submit(task: Runnable) {
        executor.submit(task)
    }

    fun shutdown() {
        executor.shutdownNow()
        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            logger.warning("Exiting with forced shutdown")
            exitProcess(0)
        }
        logger.info("Exiting normally")
    }


}