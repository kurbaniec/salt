package pass.salt.modules.db.mongo

import pass.salt.annotations.MongoDB
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import kotlin.reflect.full.isSubclassOf

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

    override fun process(className: String) {
        if (enabled) {
            val valid = SaltProcessor.processClass<MongoDB>(className)
            if (valid != null) {
                val clz = Class.forName(className).kotlin
                if (clz.isSubclassOf(MongoRepo::class)) {
                    
                    println("da")
                }
            }
        }
    }

    override fun shutdown() {

    }

}