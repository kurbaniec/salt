package pass.salt.modules.server.mapping

import pass.salt.annotations.Param
import pass.salt.exceptions.InvalidMappingParamException
import pass.salt.loader.logger
import pass.salt.modules.server.webparse.Model
import java.util.logging.Logger
import kotlin.reflect.KFunction
import kotlin.reflect.full.valueParameters

val log = Logger.getGlobal()

class Mapping(val method: HTTPMethod) {
    // mutableMapOf<String, Pair<Any, KFunction<*>>>()
    private val mapping = mutableMapOf<String, MappingFunction>()

    fun addMapping(path: String, data: MappingFunction) {
        mapping[path] = data
    }

    fun getMapping(path: String): MappingFunction? {
        return mapping[path]
    }

    class MappingFunction(
        val path: String,
        val instance: Any,
        val func: KFunction<*>) {
        var model: Model? = null
        var hasModel: Boolean = false
        val params = LinkedHashMap<String, Any>()

        init {
            funcMapper()
        }


        private fun funcMapper() {
            val parameters = func.valueParameters
            // TODO better string type check
            for (p in parameters) {
                val modelCheck = p.type.toString().replace("?", "")
                val stringCheck = p.type.toString().replace("?", "")
                if (modelCheck == Model::class.qualifiedName) {
                    model = Model()
                    hasModel = true
                    if (p.name != null) {
                        params[p.name!!] = model!!
                    }
                    else params["model"] = model!!
                }
                else if (stringCheck == String::class.qualifiedName){
                    val annotations = p.annotations
                    for (a in annotations) {
                        if (a is Param) {
                            params[a.name] = ""
                            break
                        }
                    }
                }
                else {
                    log.warning("Invalid parameter $p in function $func.")
                    log.warning("Only one Model parameter and multiple request params marked with @Param are allowed.")
                    throw InvalidMappingParamException("Invalid parameter $p in function $func.")
                }
            }
        }

        fun addParams(params: Map<String, String>) {
            for ((k, v) in params) {
                this.params[k] = v
            }

        }

        fun call(): String {
            // TODO cleaner function call?
            val array = mutableListOf<Any>(instance)
            for (v in params.values) {
                array.add(v)
            }
            val file = func.call(*array.toTypedArray()) as String
            return if (file.endsWith(".html")) file
                else "$file.html"
        }

        fun hasModel(): Boolean {
            return hasModel
        }

        /**
        private fun Pair<Any, KFunction<*>>.call(vararg args: Any?): Any? {
            val (instance, func) = this
            return func.call(instance, *args)
        }*/
    }

}

