package pass.salt.loader

import jdk.internal.org.objectweb.asm.*
import jdk.internal.org.objectweb.asm.Opcodes.ASM4
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.DefaultImpls.accept
import java.io.File
import java.io.FileInputStream
import jdk.internal.org.objectweb.asm.Opcodes.ASM4
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.DefaultImpls.accept

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