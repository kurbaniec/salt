package pass.salt.modules.db.mongo

import pass.dev.db.UserRepo
import pass.salt.annotations.MongoDB
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import kotlin.reflect.full.cast
import kotlin.reflect.full.createInstance
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
                    val type = Class.forName(className)
                    val test = MongoWrapper.getWrapper(clz.java)
                    if (test != null) {
                        val t2 = clz.cast(test)
                        val tmp = className.split(".").last()
                        val name = tmp.substring(0, 1).toLowerCase() + tmp.substring(1)
                        container.addElement(name, t2)
                    }
                }
            }
        }
    }

    override fun shutdown() {

    }

}