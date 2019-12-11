package pass.salt.code.container

import java.util.logging.Logger

/**
 * Handles all values managed by the Salt Framework.
 * Enables "Inversion of Control" and therefore Dependency Injection.
 */
class Container() {
    private val elements: MutableMap<String, Any> = mutableMapOf()
    private val logger = Logger.getLogger("SaltLogger")

    init {
        logger.fine("Container initialization done")
    }

    /**
     * Adds an element to the container with a name to associate it.
     */
    fun addElement(name: String, instance: Any) {
        if (elements.containsKey(name)) {
            logger.warning("WARNING: Container instance of $name with value [${elements[name].toString()} will" +
                    " be overwritten with value [$instance]!")
            logger.warning("Possible cause: Second object with same name initialized to container-system")
        }
        elements[name] = instance
    }

    /**
     * Adds an element to the container.
     * The classname will be the name associated with the element.
     */
    fun addElement(className: String) {
        var name = className.substring(className.lastIndexOf('.')+1, className.length)
        name = name.replace(name.first(), name.first().toLowerCase())
        addElement(name, className)
    }

    /**
     * Adds an element to the container.
     * Instead of a concrete instance, the element will be generated from its
     * classname with the default constructor.
     * The classname will be the name associated with the element.
     */
    fun addElement(name: String, className: String) {
        // TODO Exception when can´t instance object?
        //val instance = cls.getConstructor().newInstance() ?: throw ReflectionInstanceException("Could not instance object for class: \"$className\"")
        val cls = Class.forName(className)
        val instance = cls.getConstructor().newInstance()
        addElement(name, instance)
    }

    /**
     * Returns an element through its associated name or classname.
     */
    fun getElement(className: String): Any? {
        // check if full class is given
        var name = ""
        if (className.contains(".")) {
            name = className.substring(className.lastIndexOf('.')+1, className.length)
            name = name.replace(name.first(), name.first().toLowerCase())
        } else {
            name = className.replace(className.first(), className.first().toLowerCase())
        }
        return elements[name]
    }

    companion object {
        /**
         * Returns an element through its associated name or classname from a given container.
         */
        fun getInstance(className: String, container: Container): Any? {
            return container.getElement(className)
        }
    }
}