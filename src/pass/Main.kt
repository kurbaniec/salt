package pass

import pass.dev.server.Test
import pass.salt.SaltApplication
import pass.salt.loader.annotations.Get
import pass.salt.loader.parser.TOMLObject
import pass.salt.loader.parser.TOMLParser
import java.util.*
import java.util.logging.Logger
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions
import kotlin.test.assertEquals


val log: Logger = Logger.getLogger("webserver")

fun main(args: Array<String>) {
    val app = SaltApplication()
    //val loader = AnnoLoader()
    //loader.load()
    val cls = Test::class.java.kotlin
    val found = cls.memberFunctions.filter { it.annotations.any() }
    for (f in found) {
        if (f.findAnnotation<Get>() != null) println("hihi")
    }

    val functions = Test::class.java.kotlin.functions
    for (f in functions) {
        val b= f.findAnnotation<Get>()
        if (b != null) println("hi")
    }

    println("hi")
    log.info("hihi")
    println(System.getProperty("user.dir"))
    //val baum = TOMLFile("default.toml")
    testTOMLParser()
    println("hi")
    //Server()
}

fun testAnnotation() {
    /**
     * val fields = Test::class.findAnnotation<Get>()
    val test2 = Test::class.java.kotlin.findAnnotation<Get>()
    val method = Test::test
    val annotation = method.findAnnotation<Get>()

    val cls = Test::class.java.kotlin
    val found = cls.memberFunctions.filter { it.annotations.any()  }


    for(member in Test::class.java.declaredFields) {
    println(member)
    println(member.annotations.toList())  //Add .toList()
    }
    val aClass = Test::class.java
    val annotations = aClass!!.getAnnotations()
    for (annotation in annotations) {
    if (annotation is Get) {
    val myAnnotation = annotation as Get
    System.out.println("name: " + myAnnotation.path)
    }
    }
     */
}

fun testTOMLParser() {
    val baum = TOMLParser("test.toml")
    assertEquals("8080", baum.findAttribute("port"))
    val t2: TOMLObject = baum.findObject("servers.alpha") as TOMLObject
    assertEquals("eqdc10", t2.attributes["dc"])
    val t3 = baum.findObjectAttribute("clients", "data") as ArrayList<ArrayList<*>>
    assertEquals("gamma", t3[0][0])
}

