package pass.salt.modules.db.mongo

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.MongoClient as MHelp
import com.mongodb.MongoSocketException
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import java.util.logging.Level
import java.util.logging.Logger
import com.mongodb.MongoClientSettings
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider







class MongoInit(
    val config: Config,
    val container: Container
): SaltProcessor {
    var enabled = false
    val log = Logger.getLogger("SaltLogger")
    lateinit var mongoClient: MongoClient
    lateinit var db: MongoDatabase
    lateinit var collName: String
    lateinit var collection: MongoCollection<Document>

    override fun process(className: String) {
        enabled = config.findObjectAttribute("mongo", "enable")
        if (enabled) {
            try {
                disableLogger()
                //mongoClient = MongoClient(MongoClientURI(config.findObjectAttribute<String>("mongo", "uri")))
                val pojoCodecRegistry = fromRegistries(MHelp.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()))
                val settings = MongoClientSettings.builder()
                        .codecRegistry(pojoCodecRegistry)
                        .applyConnectionString(ConnectionString(config.findObjectAttribute<String>("mongo", "uri")))
                        .build()
                mongoClient = MongoClients.create(settings)
                db = mongoClient.getDatabase(config.findObjectAttribute<String>("mongo", "db"))
                collName = config.findObjectAttribute<String>("mongo", "collection")
                collection = db.getCollection(collName)
                log.fine("Successfully established connection to MongoDB")
                container.addElement("mongoInit", this)
                MongoWrapper.config = config
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
        Logger.getLogger("org.mongodb.driver").level = Level.OFF
        Logger.getLogger("JULLogger").level = Level.OFF

        /**
        var mongoLogger = Logger.getLogger("com.mongodb")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.cluster")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.connection")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.management")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.insert")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.query")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver.protocol.update")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.driver")
        mongoLogger.level = Level.SEVERE
        mongoLogger = Logger.getLogger("com.mongodb.diagnostics.logging.JULLogger")
        mongoLogger.level = Level.SEVERE*/
    }

}