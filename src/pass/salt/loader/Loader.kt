package pass.salt.loader
import java.io.File
import pass.salt.annotations.modules.AnnotationProcessor
import pass.salt.annotations.Get
import pass.salt.annotations.Controller
import pass.salt.container.Container
import pass.salt.loader.config.Config


class Loader() {
    private val config: Config
    private val container: Container

    init {
        config = Config()
        container = Container()

        //val cls = Class.forName("pass.HandlerThread")
        val path = System.getProperty("user.dir")
        // TODO safety check
        val pack = File("$path/src").list().get(0)
        annotationProcessor(path, pack)
    }

    /**
     * Look for annotations in "out/.../pack" (compile output) and process them.
     * [pack] is the name of the main package.
     * Also adds all classes to the "container".
     */
    private fun annotationProcessor(path: String, pack: String) {
        var location: File = File(pack)
        File("$path/out").walk().forEach {
            if (it.toString().endsWith(pack))
                location = it
        }
        location.walk().forEach {
            // TODO do something about KT classes
            // TODO what do do with salt path?
            if (it.toString().endsWith(".class") &&
                    !((it.toString().endsWith("Kt.class")) || it.toString().endsWith("$1.class") ||
                            it.toString().contains("salt"))) {
                val className = getClassName(it.toString(), pack)
                container.addElement(className)
                val dada = AnnotationProcessor.process<Controller, Get>(className)
                println(dada)
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
}