package pass.salt.code.exceptions

import java.io.PrintWriter
import java.io.StringWriter
import java.lang.Exception

class ReflectionInstanceException(message: String) : Exception(message)

class ConfigException(message: String) : Exception(message)

class MainPackageNotFoundException(message: String) : Exception(message)

class InvalidMappingParamException(message: String) : Exception(message)

class InvalidSecurityConfigurationException(message: String) : Exception(message)

/**
 * Is thrown, when the given attribute type does not match the type of the found attribute.
 */
class InvalidConfigTypeGivenException(message: String): Exception(message)

/**
 * Is thrown, when the given attribute is not found in the Salt configuration file.
 */
class NoSuchConfigException(message: String): Exception(message)

class InvalidParameterCountInURL(message: String): Exception(message)

class KeyStoreNotFoundException(message: String): Exception(message)

class ExceptionsTools {
    companion object {
        fun exceptionToString(ex: Exception): String {
            val sw = StringWriter()
            ex.printStackTrace(PrintWriter(sw))
            return sw.toString()
        }
    }
}