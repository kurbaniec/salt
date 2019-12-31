package pass.salt.code.modules

import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.db.mongo.MongoInit
import pass.salt.code.modules.db.mongo.MongoScan
import pass.salt.code.modules.server.mapping.MappingScan
import pass.salt.code.modules.server.PepperServer
import pass.salt.code.modules.server.security.SaltSecurity
import pass.salt.code.modules.server.security.SecurityScan

import java.util.logging.Logger
import kotlin.Exception
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

/**
 * Defines an interface that all Salt modules are build on.
 * A module can be used as a single module or class module.
 * A single module enables functionality through itself.
 * A class module enables functionality through processing classes.
 */
interface SaltProcessor {

    /**
     * Process module.
     */
    fun process(className: String = "")

    /**
     * Shutdown module.
     */
    fun shutdown()

    companion object {
        val logger = Logger.getLogger("SaltLogger")

        /**
         * Simple module factory. All modules are created and returned through this method.
         */
        fun module(module: String = "",
                   config: Config,
                   container: Container): SaltProcessor {
            try {
                return when(module) {
                    "ComponentScan" -> ComponentScan(config, container)
                    "AutowiredScan" -> AutowiredScan(container)
                    "SaltThreadPool" -> SaltThreadPoolFactory(config, container)
                    "PepperServer" -> PepperServer(config, container)
                    "MappingScan" -> MappingScan(config, container)
                    "SaltSecurity" -> SaltSecurity(config, container)
                    "SecurityScan" -> SecurityScan(config, container)
                    "MongoInit" -> MongoInit(config, container)
                    "MongoScan" -> MongoScan(config, container)
                    else -> ModuleNotFound()
                }
            } catch (ex: Exception) {
                logger.warning("Not enough arguments provided")
            }
            return ModuleNotFound()
        }

        /**
         * Returns classname when a class with the specified annotation is found, else null.
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

        /**
         * Returns properties of a class that are annotated with the given annotation.
         */
        inline fun<reified A : Annotation> processProp(className: String): MutableList<KMutableProperty<*>>? {
            val cls = Class.forName(className)
            val properties = cls.kotlin.memberProperties
            val list = mutableListOf<KMutableProperty<*>>()
            for (p in properties) {
                val a = p.findAnnotation<A>()
                if (a != null && p is KMutableProperty<*>) {
                    list.add(p)
                }
            }
            return if (list.size > 0) list else null
        }

        /**
         * Searches for specific functions annotated with annotation A in a class that is annotated with annotation C.
         * Found functions will be returned as Pairs with their annotation
         */
        inline fun<reified C : Annotation, reified A : Annotation> processClassFunc(className: String): MutableList<Pair<Annotation, KFunction<*>>>? {
            val cls = Class.forName(className)
            val annotations = cls.kotlin.annotations
            for (a in annotations) {
                if (a is C) {
                    val list = mutableListOf<Pair<Annotation, KFunction<*>>>()
                    val functions = cls.kotlin.functions
                    for (f in functions) {
                        val b = f.findAnnotation<A>()
                        if (b != null) {
                            list.add(Pair(b, f))
                        }
                    }
                    return list
                }
            }
            return null
        }
    }
}