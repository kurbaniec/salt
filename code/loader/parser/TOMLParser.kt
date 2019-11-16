package pass.salt.code.loader.parser

import java.io.File
import java.util.logging.Logger

class TOMLParser(fileName: String) {
    private var hierarchy = TOMLFile(fileName)
    private val log = Logger.getGlobal()

    fun findAttribute(name: String): Any? {
        if(hierarchy.attributes.containsKey(name)) {
            return hierarchy.attributes[name]
        }
        return null
    }

    /**
     * Replaces attribute value or creates new attribute with the given [value].
     */
    fun replaceAttribute(name: String, value: Any) {
        hierarchy.attributes[name] = value
    }

    fun findObject(name: String): TOMLObject? {
        var search = name.split(".")
        var current = TOMLObject()
        if (hierarchy.objects.containsKey(search[0])) {
            current = hierarchy.objects[search[0]]!!
            if (search.size == 1) return current
        } else return null
        search = search.subList(1, search.size)
        for ((index, value) in search.withIndex()) {
            if (current.subobjects.containsKey(value)) {
                if (index == search.size - 1) {
                    return current.subobjects[value]
                }
                current = current.subobjects[value]!!
            } else return null
        }
        return null
    }

    fun findObjectAttribute(obj: String, attr: String): Any? {
        var search = obj.split(".")
        var current = TOMLObject()
        if (hierarchy.objects.containsKey(search[0])) {
            current = hierarchy.objects[search[0]]!!
            if (search.size == 1) {
                return if (current.attributes.containsKey(attr)) {
                    current.attributes[attr]
                } else null
            }
        } else return null
        search = search.subList(1, search.size)
        for ((index, value) in search.withIndex()) {
            if (current.subobjects.containsKey(value)) {
                if (index == search.size - 1) {
                    current = current.subobjects[value]!!
                    return if (current.attributes.containsKey(attr)) {
                        current.attributes[attr]
                    } else null
                }
                current = current.subobjects[value]!!
            } else return null
        }
        return null
    }

    /**
     * Replaces the value of an attribute [name] of an object [obj] with the
     * given [value]. Does nothing when the object is not found.
     */
    fun replaceObjectAttribute(obj: String, name: String, value: Any) {
        findObject(obj)?.replaceAttribute(name, value)
    }

    /**
     * Overwrite this config with the [other] one.
     * TODO test overdrive - highly experimental
     */
    fun overdrive(other: TOMLParser) {
        // Replace attributes
        for ((key, value) in other.hierarchy.attributes) {
            this.hierarchy.attributes[key] = value
        }
        for ((key, value) in other.hierarchy.objects) {
            // Replace object attributes
            for ((k, v) in value.attributes) {
                replaceObjectAttribute(key, k, v)
            }
            subOverdrive(key, value.subobjects)

        }
    }

    fun subOverdrive(name: String, subobj: HashMap<String, TOMLObject>) {
        for ((key, obj) in subobj) {
            val objName = "$name.$key";
            for ((attrName, value) in obj.attributes) {
                replaceObjectAttribute(objName, attrName, value)
            }
            if (obj.subobjects.size > 0) {
                subOverdrive(objName, obj.subobjects)
            }
        }
    }
}

class TOMLFile(fileName: String) {
    var attributes: HashMap<String, Any> = hashMapOf()
    var objects: HashMap<String, TOMLObject> = hashMapOf()
    var current = TOMLObject()
    private val log = Logger.getGlobal()

    init {
        var attr = true
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource(fileName)
        val file = if (resource != null) {
            File(resource.file)
        }
        else {
            val path = System.getProperty("user.dir")
            log.info("$path/res/$fileName")
            File("$path/res/$fileName")
        }
        file.forEachLine {
            if(it != "") {
                val line = it.trim()
                if (attr) {
                    when {
                        line[0] == '#' -> { }
                        line[0] == '[' && line.contains(']') -> {
                            attr = false
                            val name = line.substring(1, line.indexOf(']'))
                            current = TOMLObject(name)
                            objects[name] = current
                        }
                        line.contains('=') -> {
                            val ret = this.readAttribute(line)
                            attributes[ret.first] = ret.second
                        }
                    }
                } else {
                    when {
                        line[0] == '#' -> { }
                        line[0] == '[' && line.contains(']') -> {
                            readObject(line)
                        }
                        line.contains('=') -> {
                            val ret = this.readAttribute(line)
                            current.attributes[ret.first] = ret.second
                        }
                    }
                }
            }
        }
    }

    private fun readObject(line: String) {
        val fullName = line.substring(1, line.indexOf(']'))
        var name = fullName.split(".")
        if(objects.containsKey(name[0])) {
            var parent = objects[name[0]]
            name = name.subList(1, name.size)
            for (el in name) {
                if (parent != null) {
                    if (parent.subobjects.containsKey(el)) {
                        parent = parent.subobjects[el]
                    }
                    else {
                        current = TOMLObject(el)
                        parent.subobjects[el] = current
                    }
                }
            }
        }
        else {
            current = TOMLObject(name[0])
            objects[name[0]] = current
        }
    }

    /**
     * TODO complex type support
     */
    private fun readAttribute(line: String): Pair<String, Any> {
        val name = line.substring(0, line.indexOf(' '))
        val value = if (line.contains('#') && !line.contains("\"#")) {
            line.substring(name.length + 3, line.indexOf('#')-1)
        }
        else line.substring(name.length + 3)
        val parser = AttributeParser.check(value)
        return Pair(name, parser.parse(value))
    }
}



class TOMLObject() {
    var name = ""
    var attributes: HashMap<String, Any> = HashMap()
    var subobjects: HashMap<String, TOMLObject> = HashMap()

    constructor(name: String) : this() {
        this.name = name
    }

    fun replaceAttribute(name: String, value: Any) {
        attributes[name] = value
    }
}