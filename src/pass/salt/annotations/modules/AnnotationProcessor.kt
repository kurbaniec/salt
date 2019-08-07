package pass.salt.annotations.modules

import pass.salt.annotations.Controller
import pass.salt.container.Container
import pass.salt.loader.config.Config

import java.util.logging.Logger
import kotlin.Exception
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

interface AnnotationProcessor {

    fun process()

    companion object {
        val logger = Logger.getLogger("webserver")
        /**
         * [name of module][config][container]
         */
        fun module(vararg dep: Any): AnnotationProcessor {
            try {
                val module = dep[0] as String
                val className = dep[1] as String
                val config = dep[2] as Config
                val container = dep[3] as Container
                return when(module) {
                    "ComponentScan" -> ComponentScan(className, config, container)
                    else -> ModuleNotFound()
                }
            } catch (ex: Exception) {
                logger.warning("Not enough arguments provided")
            }
            return ModuleNotFound()
        }

        /**
         * Returns classname when a class with the specified Annotation is found, else null.
         */
        inline fun<reified C : Annotation> processClass(className: String): String? {
            val cls = Class.forName(className)
            val annotations = cls.kotlin.annotations
            // check annotations
            for (a in annotations) {
                if (a is C) {
                    return className
                }
                // check meta-annotations
                else {
                    for(a2 in a.annotationClass.annotations) {
                        if (a2 is C) {
                            return className
                        }
                    }
                }
            }
            return null
        }

        inline fun<reified C : Annotation, reified A : Annotation> processFunc(className: String): Pair<Any, MutableList<Any>>? {
            val cls = Class.forName(className)
            val annotations = cls.kotlin.annotations
            for (a in annotations) {
                if (a is C) {
                    val list = mutableListOf<Any>()
                    val functions = cls.kotlin.functions
                    for (f in functions) {
                        val b = f.findAnnotation<A>()
                        if (b != null) {
                            list.add(f)
                            //f.call(instance)
                        }
                    }
                    return Pair(className, list)
                }
            }
            return null
        }

        inline fun<reified C : Annotation, reified A : Annotation> processProp(className: String): Pair<Any, MutableList<Any>>? {
            val cls = Class.forName(className)
            val annotations = cls.kotlin.annotations
            for (a in annotations) {
                if (a is C) {
                    val list = mutableListOf<Any>()
                    val properties = cls.kotlin.memberProperties
                    for (p in properties) {
                        val b = p.findAnnotation<A>()
                        if (b != null) {
                            list.add(p)
                        }
                    }
                    return Pair(className, list)
                }
            }
            return null
        }
    }
}