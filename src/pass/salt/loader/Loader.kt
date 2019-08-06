package pass.salt.loader
import pass.salt.*
import pass.dev.*
import java.io.File
import pass.dev.server.Test
import pass.salt.loader.annotations.Get
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import com.sun.org.apache.bcel.internal.util.ClassPath
import com.sun.xml.internal.bind.v2.model.core.ClassInfo



class Loader() {
    init {
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
                val cls = Class.forName(name)
                /**for(annotation in cls.declaredAnnotations) {
                    println(annotation)
                }*/
                val functions = cls.kotlin.functions
                for (f in functions) {
                    val b = f.findAnnotation<Get>()
                    if (b != null) println("Anno")
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
/**
class AnnotationScanner(api: Int) : ClassVisitor(api) {

    internal class AnnotationMethodsScanner : AnnotationVisitor(Opcodes.ASM4) {

        internal class AnnotationMethodsArrayValueScanner : AnnotationVisitor(Opcodes.ASM4) {

            override fun visit(name: String, value: Any) {
                println("Ar.visit: value=$value")
                super.visit(name, value)
            }
        }

        override fun visitEnum(name: String, desc: String, value: String) {
            println("A.visitEnum: name=$name desc=$desc value=$value")
            super.visitEnum(name, desc, value)
        }

        override fun visitAnnotation(name: String, desc: String): AnnotationVisitor {
            println("A.visitAnnotation: name=$name desc=$desc")
            return super.visitAnnotation(name, desc)
        }

        override fun visitArray(name: String): AnnotationVisitor {
            return AnnotationMethodsArrayValueScanner()
        }
    }

    internal class FieldAnnotationScanner : FieldVisitor(Opcodes.ASM4) {

        override fun visitAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
            println("F.visitAnnotation: desc=$desc")
            return AnnotationMethodsScanner()
        }
    }

    internal class MethodAnnotationScanner : MethodVisitor(Opcodes.ASM4) {

        override fun visitAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
            println("M.visitAnnotation: desc=$desc")
            return super.visitAnnotation(desc, visible)
        }
    }

    override fun visitAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
        println("\nvisitAnnotation: desc=$desc")
        return AnnotationMethodsScanner()
    }

    override fun visitField(access: Int, name: String, desc: String, signature: String, value: Any): FieldVisitor {
        println("\nvisitField: name=$name desc=$desc")
        return FieldAnnotationScanner()
    }

    override fun visitMethod(
        access: Int,
        name: String,
        desc: String,
        signature: String,
        exceptions: Array<String>
    ): MethodVisitor {
        println("\nvisitMethod: name=$name desc=$desc")
        return MethodAnnotationScanner()
    }

    /**companion object {

        @Throws(Exception::class)
        @JvmStatic
        fun main(args: Array<String>) {
            for (arg in args) {
                val `in` = FileInputStream(File(arg))
                val cr = ClassReader(`in`)
                cr.accept(AnnotationScanner(Opcodes.ASM4), 0)
            }
        }
    }*/
}*/