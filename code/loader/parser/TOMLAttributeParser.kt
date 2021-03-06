package pass.salt.code.loader.parser

/**
 * Interfaces that defines parsing behaviour.
 * Also, includes a method for correct parsing of an given String.
 */
interface AttributeParser {
    /**
     * Parse String value to correct type of given value.
     */
    fun parse(attribute: String): Any
    companion object {
        fun check(attribute: String): AttributeParser {
            val intRegex = """[+-]?[0-9]+""".toRegex()
            val floatRegex = """[+-]?[0-9]+[.][0-9]+""".toRegex()
            return when {
                attribute[0] == '[' && attribute[attribute.length-1] == ']' -> ArrayParser()
                attribute.toLowerCase() == "true" || attribute.toLowerCase() == "false" -> BooleanParser()
                floatRegex.matches(attribute) -> FloatParser()
                intRegex.matches(attribute) -> IntegerParser()
                else -> StringParser()
            }
        }
    }
}

/**
 * Used for parsing Arrays.
 */
class ArrayParser: AttributeParser {
    override fun parse(attribute: String): Any {
        return convert(attribute)
    }

    private fun convert(attribute: String): MutableList<Any> {
        //val result: List<Any> = attribute.trim().removeSurrounding("[", "]").split(",").map {it.trim()}
        var flag = false
        val result: List<Any> = if (attribute[0] == '[') {
            flag = true
            attribute.trim().removeSurrounding("[", "]").split("],").map {
                it.trim()
                it.replace("[", "").replace("]", "")
            }
        }
        else {
            attribute.trim().removeSurrounding("[", "]").split(",").map {it.trim()}
        }

        if (flag) {
            val list = result.toMutableList()
            for (i in list.indices) {
                list[i] = convert(list[i].toString())
            }
            return list
        }
        else {
           return parseIntern(result)
        }
    }

    private fun parseIntern(attribute: List<Any>): MutableList<Any> {
        val list = attribute.toMutableList()
        val parser = AttributeParser.check(list[0].toString())
        for (i in list.indices) {
            list[i] = parser.parse(list[i].toString())
        }
        return list
    }
}

/**
 * Used for parsing booleans.
 */
class BooleanParser: AttributeParser {
    override fun parse(attribute: String): Boolean {
        return attribute.toBoolean()
    }
}

/**
 * Used for parsing String.
 */
class StringParser: AttributeParser {
    override fun parse(attribute: String): String {
        return attribute.substring(1, attribute.length-1)
    }
}

/**
 * Used for parsing Integers.
 */
class IntegerParser: AttributeParser {
    override fun parse(attribute: String): Int {
        return attribute.toInt()
    }
}

/**
 * Used for parsing Floats.
 */
class FloatParser: AttributeParser {
    override fun parse(attribute: String): Float {
        return attribute.toFloat()
    }
}

