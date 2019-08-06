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

    log.info("hihi")
    //testTOMLParser()
    //Server()
}

fun testTOMLParser() {
    val baum = TOMLParser("test.toml")
    assertEquals("8080", baum.findAttribute("port"))
    val t2: TOMLObject = baum.findObject("servers.alpha") as TOMLObject
    assertEquals("eqdc10", t2.attributes["dc"])
    val t3 = baum.findObjectAttribute("clients", "data") as ArrayList<ArrayList<*>>
    assertEquals("gamma", t3[0][0])
}

