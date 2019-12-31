package pass.salt.code.modules.db.mongo

import com.mongodb.BasicDBObject
import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.MongoClient as MHelp
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor
import java.util.logging.Level
import java.util.logging.Logger
import com.mongodb.MongoClientSettings
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import kotlin.system.exitProcess

/**
 * Initializes the Salt application to work with MongoDB.
 */
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

    /**
     * Establish connection to MongoDB.
     */
    override fun process(className: String) {
        enabled = config.findObjectAttribute("mongo", "enable")
        if (enabled) {
            try {
                disableLogger()
                val pojoCodecRegistry = fromRegistries(MHelp.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()))
                val settings = MongoClientSettings.builder()
                        .codecRegistry(pojoCodecRegistry)
                        .applyConnectionString(ConnectionString(config.findObjectAttribute<String>("mongo", "uri")))
                        .build()
                mongoClient = MongoClients.create(settings)
                db = mongoClient.getDatabase(config.findObjectAttribute<String>("mongo", "db"))
                db.runCommand(BasicDBObject("ping", "1"))
                collName = config.findObjectAttribute<String>("mongo", "collection")
                collection = db.getCollection(collName)
                log.fine("Successfully established connection to MongoDB")
                container.addElement("mongoInit", this)
                MongoWrapper.config = config
            }
            catch (ex: Exception) {
                log.warning("Could not establish database connection, MongoDB will not be used")
                log.warning("Check your [mongo] configuration in config.toml")
                enabled = false
                // TODO add try-catch to loader and shutdown all threads on exception
                // throw MongoInitExecption("Check your Database status and start the Salt application again")
                exitProcess(1)
            }
        }
    }

    /**
     * Close MongoDB connection.
     */
    override fun shutdown() {
        // TODO MongoDb shutdown
    }

    private fun disableLogger() {
        Logger.getLogger("org.mongodb.driver").level = Level.OFF
        Logger.getLogger("JULLogger").level = Level.OFF
    }

}