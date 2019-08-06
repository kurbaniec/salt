package pass.salt.loader
import pass.salt.*
import pass.dev.*
import java.io.File
import pass.dev.server.Test
import pass.salt.loader.annotations.AnnotationProcessor
import pass.salt.loader.annotations.Get
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import pass.salt.loader.annotations.Controller
import pass.salt.loader.config.Config


class Loader() {
    init {
        Config()


        //val cls = Class.forName("pass.HandlerThread")
        val path = System.getProperty("user.dir")
        // TODO safety check
        val pack = File("$path/src").list().get(0)
        annotationProcessor(path, pack)
    }

    private fun annotationProcessor(path: String, pack: String) {
        var location: File = File(pack)
        File("$path/out").walk().forEach {
            if (it.toString().endsWith(pack))
                location = it
        }
        location.walk().forEach {
            //println(it)
            // TODO do something about KT classes
            if (it.toString().endsWith(".class") &&
                    !((it.toString().endsWith("Kt.class")) || it.toString().endsWith("$1.class"))) {
                val name = getClassName(it.toString(), pack)
                val dada = AnnotationProcessor.process<Controller, Get>(name)

                val cls = Class.forName(name)
                /**for(annotation in cls.declaredAnnotations) {
                    println(annotation)
                }*/
                val annotations = cls.kotlin.annotations
                for (a in annotations) {
                    if (a is Controller) println("Controller")
                }
                val functions = cls.kotlin.functions
                for (f in functions) {
                    val b = f.findAnnotation<Get>()
                    if (b != null) {
                        val test = cls.getConstructor().newInstance()
                        f.call(test)
                        //val c = cls.getDeclaredMethod("test")
                        //c.invoke(null)
                        //println("Anno")
                    }
                }
            }
            /**
            val functions = Test::class.java.kotlin.functions
            for (f in functions) {
                val b= f.findAnnotation<Get>()
                if (b != null) println("hi")
            }*/
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