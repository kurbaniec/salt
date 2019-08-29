package pass.salt.loader
import java.io.File
import pass.salt.modules.SaltProcessor
import pass.salt.container.Container
import pass.salt.exceptions.MainPackageNotFoundException
import pass.salt.loader.config.Config
import java.lang.Exception
import java.util.logging.Logger

val logger = Logger.getGlobal()

class Loader() {
    val config: Config
    val container: Container
    val singleModules = mutableListOf<SaltProcessor>()
    val classModules = mutableListOf<SaltProcessor>()

    init {
        config = Config()
        container = Container()
        container.addElement("config", config)
        load()
    }

    /**
     * Look for annotations in "out/.../pack" (compile output) and process them.
     * Also adds all classes to the "container".
     */
    private fun load(): Loader {
        val pair = getLocation()
        val pack = pair.first
        val location = pair.second
        logger.fine("Project path located")

        // Module System -> Single Instance
        logger.fine("Loading module System (Single)")
        singleModules.add(SaltProcessor.module(module = "SaltSecurity", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "SaltThreadPool", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "PepperServer", config = config, container = container))
        singleModules.add(SaltProcessor.module(module = "MongoInit", config = config, container = container))
        for (mod in singleModules) {
            mod.process()
        }

        // Module System -> Process Annotations through all Classes
        // Order of modules is important!
        logger.fine("Loading module System (Classes)")
        classModules.add(SaltProcessor.module("ComponentScan", config, container))
        classModules.add(SaltProcessor.module("MongoScan", config, container))
        classModules.add(SaltProcessor.module("AutowiredScan", config, container))
        classModules.add(SaltProcessor.module("MappingScan", config, container))
        classModules.add(SaltProcessor.module("SecurityScan", config, container))
        for (mod in classModules) {
            location.walk().forEach {
                // TODO do something about KT classes
                // TODO what do do with salt path?
                if (it.toString().endsWith(".class") &&
                        !((it.toString().endsWith("Kt.class")) || it.toString().endsWith("$1.class") ||
                                it.toString().contains("salt") || it.toString().contains("$"))) {

                    val className = getClassName(it.toString(), pack)

                    // Module System -> Process Annotations through all Classes
                    mod.process(className)
                }
            }
        }
        return this
    }

    fun shutdown() {
        for (mod in classModules) {
            mod.shutdown()
        }
        for (mod in singleModules) {
            mod.shutdown()
        }
    }

    private fun getClassName(name: String, pack: String): String {
        var ret = name.replace(".class", "")
        ret = ret.substring(ret.indexOf(pack), ret.length)
        ret = ret.replace("\\", ".")
        //return ret.substring(ret.lastIndexOf('\\')+1, ret.length)
        return ret
    }

    /**
     * [pack] is the name of the main package.
     */
    private fun getLocation(): Pair<String, File> {
        val path = System.getProperty("user.dir")
        // TODO safety check
        try {
            val pack = File("$path/src").list().get(0)!!
            File("$path/out").walk().forEach {
                if (it.toString().endsWith(pack)) {
                    return Pair(pack, it)
                }
            }
        } catch (ex: Exception) {
            logger.warning(ex.toString())
        }
        throw MainPackageNotFoundException("Main package not found under /src/")
    }
}