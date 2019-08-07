package pass.salt.loader.config

import pass.salt.loader.parser.TOMLParser
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.Logger

class Config() {
    private val config: TOMLParser = TOMLParser("default.toml")
    private val logger: Logger = Logger.getGlobal()

    init {
        try {
            config.overdrive(TOMLParser("config.toml"))
        }
        catch (ex: Exception) {
            logger.warning("Config file [config.toml] not found under /res/config.toml")
        }
        setLoggerLevel(findObjectAttribute("logger", "level") as String)
        logger.fine("Config loaded")
    }

    fun findAttribute(name: String): Any? {
        return config.findAttribute(name)
    }

    fun findObjectAttribute(obj: String, attr: String): Any? {
        return config.findObjectAttribute(obj, attr)
    }

    private fun setLoggerLevel(level: String) {
        val systemOut = ConsoleHandler()
        val lvl: Level =  when (level.toLowerCase()) {
            "all" -> Level.ALL
            "severe" -> Level.SEVERE
            "warning" -> Level.WARNING
            "info" -> Level.INFO
            "config" -> Level.CONFIG
            "fine" -> Level.FINE
            "finer" -> Level.FINER
            "finest" -> Level.FINEST
            "off" -> Level.OFF
            else -> Level.ALL
        }
        systemOut.level = lvl
        logger.addHandler( systemOut );
        logger.level = lvl;
    }
}