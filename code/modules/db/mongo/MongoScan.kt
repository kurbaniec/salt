package pass.salt.code.modules.db.mongo

import pass.salt.code.annotations.MongoDB
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor
import kotlin.reflect.full.isSubclassOf

/**
 * Scans classes for [MongoDB] annotations.
 * If a class contains it, a concrete [MongoRepo] will be initialized,
 * that contains the custom CRUD-operations from the scanned class.
 */
class MongoScan (
    val config: Config,
    val container: Container
): SaltProcessor {
    val enabled: Boolean
    val mongo: MongoInit?

    init {
        mongo = container.getElement("mongoInit") as MongoInit?
        enabled = mongo?.enabled ?: false
    }

    /**
     * Scans a class for the [MongoDB] annotations.
     * If the class contains it, a concrete [MongoRepo] will be initialized,
     * that contains the custom CRUD-operations from the scanned class.
     */
    override fun process(className: String) {
        if (enabled && mongo != null) {
            val valid = SaltProcessor.processClass<MongoDB>(className)
            if (valid != null) {
                val clz = Class.forName(className).kotlin
                if (clz.isSubclassOf(MongoRepo::class)) {
                    val type = Class.forName(className)
                    val proxy = MongoWrapper.getWrapper(clz.java, mongo.db, mongo.collName)
                    if (proxy != null) {
                        val tmp = className.split(".").last()
                        val name = tmp.substring(0, 1).toLowerCase() + tmp.substring(1)
                        container.addElement(name, proxy)
                    }
                }
            }
        }
    }

    /**
     * Not used.
     */
    override fun shutdown() {

    }

}