package pass
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.bson.Document
import pass.salt.SaltApplication
import pass.salt.loader.parser.TOMLObject
import pass.salt.loader.parser.TOMLParser
import java.util.*
import kotlin.test.assertEquals

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //mongo()
            //clear()
            val app = SaltApplication()
        }

        fun mongo() {
            val mongo = MongoClient(MongoClientURI("mongodb://Svtmgrleh04.wienkav.at:27017"))
            // MongoSocket Exception
            val db = mongo.getDatabase("dev")
            val collection = db.getCollection("test")
            collection.insertOne(Document(mapOf("key" to "value")))
            val b = collection.find()
            val c = b.count()
            println("mongo")
            collection.drop()
        }

        fun clear() {
            val mongo = MongoClient(MongoClientURI("mongodb://Svtmgrleh04.wienkav.at:27017"))
            // MongoSocket Exception
            val db = mongo.getDatabase("dev")
            //val user = db.getCollection("users")
            //user.drop()
            val pass = db.getCollection("store")
            pass.drop()
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
