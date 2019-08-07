package pass.salt.container

import java.util.logging.Logger

class Container() {
    private val elements: MutableMap<String, Any> = mutableMapOf()
    private val logger = Logger.getLogger("webserver")

    fun addElement(name: String, instance: Any) {
        if (elements.containsKey(name)) {
            logger.warning("WARNING: Container instance of $name with value [${elements[name].toString()} will" +
                    " be overwritten with value [$instance]!")
            logger.warning("Possible cause: Second object with same name initialized to container-system")
        }
        elements[name] = instance
    }

    fun addElement(className: String) {
        var name = className.substring(className.lastIndexOf('.')+1, className.length)
        name = name.replace(name.first(), name.first().toLowerCase())
        addElement(name, className)
    }

    fun addElement(name: String, className: String) {
        val cls = Class.forName(className)
        val instance = cls.getConstructor().newInstance()
        addElement(name, instance)
    }
}