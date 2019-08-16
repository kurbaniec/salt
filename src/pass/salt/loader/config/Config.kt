package pass.salt.loader.config

import pass.salt.exceptions.ExceptionsTools
import pass.salt.exceptions.InvalidConfigTypeGivenException
import pass.salt.exceptions.NoSuchConfigException
import pass.salt.loader.parser.TOMLParser
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.Logger

class Config() {
    val config: TOMLParser = TOMLParser("default.toml")
    val logger: Logger = Logger.getGlobal()

    init {
        try {
            config.overdrive(TOMLParser("config.toml"))
        }
        catch (ex: Exception) {
            logger.warning("Config file [config.toml] not found under /res/config.toml")
        }
        setLoggerLevel(findObjectAttribute<String>("logger", "level") as String)
        logger.fine("Config loaded")
    }



    @Throws(NoSuchConfigException::class, InvalidConfigTypeGivenException::class)
    inline fun <reified T>findAttribute(name: String): T {
        val found =  config.findAttribute(name)
        if (found == null) {
            val ext =  NoSuchConfigException("Config $name not found")
            logger.warning(ExceptionsTools.exceptionToString(ext))
            throw ext
        } else {
            if (found is T) {
                return found
            }
            if (T::class.simpleName!!.contains("String")) {
                val stringified = found.toString()
                return stringified as T
            }
            val ext = InvalidConfigTypeGivenException("The object $name is not from the given type T")
            logger.warning(ExceptionsTools.exceptionToString(ext))
            throw ext
        }
    }

    @Throws(NoSuchConfigException::class, InvalidConfigTypeGivenException::class)
    inline fun <reified T>findObjectAttribute(obj: String, attr: String): T {
        val found = config.findObjectAttribute(obj, attr)
        if (found == null) {
            val ext = NoSuchConfigException("Config $obj.$attr not found")
            logger.warning(ExceptionsTools.exceptionToString(ext))
            throw ext
        } else {
            if (found is T) {
                return found
            }
            if (T::class.simpleName!!.contains("String")) {
                val stringified = found.toString()
                return stringified as T
            }
            val ext = InvalidConfigTypeGivenException("The object $attr is not from the given type T")
            logger.warning(ExceptionsTools.exceptionToString(ext))
            throw ext
        }
    }

    fun setLoggerLevel(level: String) {
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