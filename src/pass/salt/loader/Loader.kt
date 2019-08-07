package pass.salt.loader
import java.io.File
import pass.salt.modules.SaltProcessor
import pass.salt.container.Container
import pass.salt.exceptions.MainPackageNotFoundException
import pass.salt.loader.config.Config
import pass.salt.modules.logger
import java.lang.Exception


class Loader() {
    private val config: Config
    private val container: Container

    init {
        config = Config()
        container = Container()
        load()
    }

    /**
     * Look for annotations in "out/.../pack" (compile output) and process them.
     * Also adds all classes to the "container".
     */
    private fun load() {
        val pair = getLocation()
        val pack = pair.first
        val location = pair.second

        // Module System -> Single Instance
        SaltProcessor.module(module = "SaltThreadPool", config = config, container = container).process()

        // Module System -> Process Annotations through all Classes
        // Order of modules is important!
        val modules = mutableListOf<SaltProcessor>()
        modules.add(SaltProcessor.module("ComponentScan", config, container))
        modules.add(SaltProcessor.module("AutowiredScan", config, container))

        for (mod in modules) {
            location.walk().forEach {
                // TODO do something about KT classes
                // TODO what do do with salt path?
                if (it.toString().endsWith(".class") &&
                        !((it.toString().endsWith("Kt.class")) || it.toString().endsWith("$1.class") ||
                                it.toString().contains("salt"))) {

                    val className = getClassName(it.toString(), pack)

                    mod.process(className)
                    //SaltProcessor.module("ComponentScan", config, container).process(className)
                    println("Blub")
                }
            }
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