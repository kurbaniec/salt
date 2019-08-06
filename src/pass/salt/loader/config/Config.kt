package pass.salt.loader.config

import pass.salt.loader.parser.TOMLParser
import java.util.logging.Logger

class Config() {
    private val config: TOMLParser = TOMLParser("default.toml")
    private val logger: Logger = Logger.getLogger("webserver")

    init {
        try {
            config.overdrive(TOMLParser("config.toml"))
        }
        catch (ex: Exception) {
            logger.warning("Config file [config.toml] not found under /res/config.toml")
        }
    }

    fun findAttribute(name: String): Any? {
        return config.findAttribute(name)
    }

    fun findObjectAttribute(obj: String, attr: String): Any? {
        return config.findObjectAttribute(obj, attr)
    }
}