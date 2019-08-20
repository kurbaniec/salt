package pass.salt.modules.db.mongo

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.MongoSocketException
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.logger
import java.util.logging.Level
import java.util.logging.Logger
import java.util.logging.Level.SEVERE



class MongoInit(
    val config: Config,
    val container: Container
): SaltProcessor {
    var enabled = false
    val log = Logger.getGlobal()
    lateinit var mongoClient: MongoClient
    lateinit var db: MongoDatabase
    lateinit var collection: MongoCollection<Document>

    override fun process(className: String) {
        enabled = config.findObjectAttribute("mongo", "enable")
        if (enabled) {
            try {
                disableLogger()
                mongoClient = MongoClient(MongoClientURI(config.findObjectAttribute<String>("mongo", "uri")))
                db = mongoClient.getDatabase(config.findObjectAttribute<String>("mongo", "db"))
                collection = db.getCollection(config.findObjectAttribute<String>("mongo", "collection"))
                log.fine("Successfully established connection to MongoDB")
                container.addElement("mongoInit", this)
            }
            catch (ex: MongoSocketException) {
                log.warning("Could not establish database connection, MongoDB will not bes used")
                log.warning("Check your [mongo] configuration in config.toml")
                enabled = false
            }
        }
    }

    override fun shutdown() {

    }

    private fun disableLogger() {
        var mongoLogger = Logger.getLogger("com.mongodb")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.cluster")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.connection")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.management")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.insert")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.query")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.update")
        mongoLogger.level = Level.OFF
        mongoLogger = Logger.getLogger("com.mongodb.driver")
        mongoLogger.level = Level.OFF
    }

}