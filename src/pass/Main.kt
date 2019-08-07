package pass

import pass.salt.SaltApplication
import pass.salt.loader.parser.TOMLObject
import pass.salt.loader.parser.TOMLParser
import java.util.*
import kotlin.test.assertEquals

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val app = SaltApplication()
            //Server()
        }
    }

    fun testTOMLParser() {
        val baum = TOMLParser("test.toml")
        assertEquals("8080", baum.findAttribute("port"))
        val t2: TOMLObject = baum.findObject("servers.alpha") as TOMLObject
        assertEquals("eqdc10", t2.attributes["dc"])
        val t3 = baum.findObjectAttribute("clients", "data") as ArrayList<ArrayList<*>>
        assertEquals("gamma", t3[0][0])
    }
}
