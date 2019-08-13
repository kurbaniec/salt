package pass.salt.modules.server.webparse

import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class Model {
    val attributes = mutableMapOf<String, Any>()

    fun addAttribute(name: String, attribute: Any) {
        attributes[name] = attribute
    }

    fun getAttribute(name: String): String {
        return attributes[name].toString()
    }

    fun getAttribute(name: String, attribute: String): String {
        val instance = attributes[name]
        if (instance != null) {
            val property = instance.javaClass.kotlin.memberProperties.first {
                it.name == attribute
            }
            return property.get(instance).toString()
        }
        return ""
    }


}