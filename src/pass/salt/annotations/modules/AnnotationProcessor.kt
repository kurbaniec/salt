package pass.salt.annotations.modules

import java.lang.Exception
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

interface AnnotationProcessor {
    companion object {
        inline fun<reified C : Annotation, reified A : Annotation> process(className: String): Pair<Any, MutableList<Any>>? {
            val cls = Class.forName(className)
            val annotations = cls.kotlin.annotations
            for (a in annotations) {
                if (a is C) {
                    // TODO Exception when can´t instance object?
                    //val instance = cls.getConstructor().newInstance() ?: throw ReflectionInstanceException("Could not instance object for class: \"$className\"")
                    val instance: Any
                    try {
                        instance = cls.getConstructor().newInstance()
                    }catch (ex: Exception) {
                        println(ex)
                        return null
                    }
                    val list = mutableListOf<Any>()
                    val functions = cls.kotlin.functions
                    for (f in functions) {
                        println(f.javaClass)
                        val b = f.findAnnotation<A>()
                        if (b != null) {
                            list.add(f)
                            //f.call(instance)
                        }
                    }
                    return Pair(instance, list)
                }
            }
            return null
        }
    }
}