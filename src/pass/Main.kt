package pass

import pass.dev.server.Test
import pass.salt.SaltApplication
import pass.salt.annotations.Get
import pass.salt.loader.parser.TOMLObject
import pass.salt.loader.parser.TOMLParser
import pass.salt.server.Server
import java.util.*
import java.util.logging.Logger
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberFunctions
import kotlin.test.assertEquals


val log: Logger = Logger.getLogger("webserver")

fun main(args: Array<String>) {
    val app = SaltApplication()
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

