package pass.salt.loader.annotations

import jdk.internal.org.objectweb.asm.ClassVisitor

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Get(val path: String)



