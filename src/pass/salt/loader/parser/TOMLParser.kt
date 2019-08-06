package pass.salt.loader.parser

import java.io.File

class TOMLParser(fileName: String) {
    private var hierarchy = TOMLFile(fileName)

    fun findAttribute(name: String): Any? {
        if(hierarchy.attributes.containsKey(name)) {
            return hierarchy.attributes[name]
        }
        return null
    }

    fun findObject(name: String): Any? {
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
}

class TOMLFile(fileName: String) {
    var attributes: HashMap<String, Any> = hashMapOf()
    var objects: HashMap<String, TOMLObject> = hashMapOf()
    var current = TOMLObject()

    init {
        var attr = true
        val classLoader = javaClass.classLoader
        val resource = classLoader.getResource(fileName)
        File(resource.file).forEachLine {
            if(it != "") {
                val line = it.trim()
                if (attr) {
                    when {
                        line[0] == '#' -> { }
                        line[0] == '[' && line.contains(']') -> {
                            attr = false
                            current = TOMLObject()
                            val name = line.substring(1, line.indexOf(']'))
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
                        current = TOMLObject()
                        parent.subobjects[el] = current
                    }
                }
            }
        }
        else {
            current = TOMLObject()
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
    var attributes: HashMap<String, Any> = HashMap()
    var subobjects: HashMap<String, TOMLObject> = HashMap()
}