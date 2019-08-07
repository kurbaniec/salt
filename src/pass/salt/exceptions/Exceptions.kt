package pass.salt.exceptions

import java.lang.Exception

class ReflectionInstanceException(message: String) : Exception(message)

class ConfigException(message: String) : Exception(message)

class MainPackageNotFoundException(message: String) : Exception(message)