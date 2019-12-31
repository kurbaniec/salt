package pass.salt.code.modules.server.webparse

import kotlin.reflect.full.memberProperties
import pass.salt.code.modules.server.webparse.Webparse


/**
 * Supplies attributes that are used when parsing a html-site with [Webparse].
 */
class Model {
    val attributes = mutableMapOf<String, Any>()
    val attributeLists = mutableMapOf<String, List<Any>>()

    /**
     * Add attribute to model.
     */
    fun addAttribute(name: String, attribute: Any) {
        attributes[name] = attribute
    }

    /**
     * Add attribute that is list to model.
     */
    fun addAttribute(name: String, attribute: List<Any>) {
        attributeLists[name] = attribute
    }

    /**
     * Get attribute by name.
     */
    fun getAttribute(name: String): String {
        return attributes[name].toString()
    }

    /**
     * Get list-attribute by name.
     */
    fun getAttributeList(name: String): List<Any> {
        val res = attributeLists[name]
        if (res is List<*>) {
            return res
        }
        return listOf()
    }

    /**
     * Get string representation of a given property of an attribute.
     */
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

    /**
     * Clear model.
     */
    fun clear() {
        attributes.clear()
        attributeLists.clear()
    }


}