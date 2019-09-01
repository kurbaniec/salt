package pass.salt.modules.server.mapping

import pass.salt.annotations.Param
import pass.salt.exceptions.ExceptionsTools
import pass.salt.exceptions.InvalidMappingParamException
import pass.salt.exceptions.InvalidParameterCountInURL
import pass.salt.modules.server.HTTPTransport
import pass.salt.modules.server.security.SessionUser
import pass.salt.modules.server.webparse.Model
import java.io.File
import java.util.logging.Logger
import kotlin.reflect.KFunction
import kotlin.reflect.full.valueParameters


val log = Logger.getLogger("Mapping")

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
        var sessionUser: SessionUser? = null
        var hasSessionUser = false
        var sessionUserKey = "sessionUser"
        val params = LinkedHashMap<String, Any>()

        init {
            funcMapper()
        }

        private fun funcMapper() {
            val parameters = func.valueParameters
            // TODO better string type check
            for (p in parameters) {
                val check = p.type.toString().replace("?", "")
                if (check == Model::class.qualifiedName) {
                    model = Model()
                    hasModel = true
                    if (p.name != null) {
                        params[p.name!!] = model!!
                    }
                    else params["model"] = model!!
                }
                else if (check == SessionUser::class.qualifiedName) {
                    hasSessionUser = true
                    if (p.name != null) {
                        params[p.name!!] = SessionUser()
                        sessionUserKey = p.name!!
                    }
                    else params["sessionUser"] = SessionUser()
                }
                else if (check == String::class.qualifiedName){
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
                if (this.params.containsKey(k)) this.params[k] = v
            }

        }

        fun call(): Any {
            val array = mutableListOf<Any>(instance)
            // TODO check if param mapping still works
            /**
            val file = if(func.valueParameters.size == params.values.size) {
                for (v in params.values) {
                    array.add(v)
                }
                func.call(*array.toTypedArray()) as String
            } else "404"*/
            var file: Any? = null
            if(func.valueParameters.size == params.values.size) {
                for (v in params.values) {
                    array.add(v)
                }
                file = func.call(*array.toTypedArray())
            }
            else {
                val exp = InvalidParameterCountInURL("URL Mapping exited with error")
                log.warning(ExceptionsTools.exceptionToString(exp))
                throw exp
            }
            return if (file is String) {
                if (file.endsWith(".html")) file
                else "$file.html"
            } else if (file is HTTPTransport) {
                file
            } else ""
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

