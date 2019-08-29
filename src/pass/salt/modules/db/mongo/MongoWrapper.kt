package pass.salt.modules.db.mongo

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates.combine
import com.mongodb.client.model.Updates.set
import org.bson.BSON
import org.bson.conversions.Bson
import pass.salt.exceptions.ExceptionsTools
import pass.salt.loader.config.Config
import java.lang.Exception
import java.lang.reflect.*
import java.lang.reflect.ParameterizedType
import java.util.logging.Logger

class MongoWrapper: InvocationHandler {
    lateinit var clazz: Class<*>
    lateinit var db: MongoDatabase
    lateinit var collName: String
    lateinit var collection: MongoCollection<Any>
    lateinit var types: Pair<Class<*>, Class<*>>
    lateinit var type: String
    val log = Logger.getGlobal()

    companion object {
        lateinit var config: Config

        fun  <W>getWrapper(cls: Class<W>, db: MongoDatabase, collName: String): W {
            val wrappy = MongoWrapper()
            wrappy.clazz = cls
            wrappy.db = db
            wrappy.collName = collName
            init(wrappy) // TODO what if wrappy init fails?
            return Proxy.newProxyInstance(cls.classLoader, arrayOf(cls), wrappy) as W
        }

        fun init(wrappy: MongoWrapper): Boolean {
            var types: Array<Type>? = null
            val ifaces = wrappy.clazz.genericInterfaces
            if (ifaces != null) {
                for (ifc in ifaces) {
                    if (ifc is ParameterizedType && ifc.rawType.typeName.endsWith("MongoRepo")) {
                        types = ifc.actualTypeArguments
                        wrappy.types = Pair(types.first() as Class<*>, types.last() as Class<*>)
                        val tmp = (types.first() as Class<*>).simpleName
                        wrappy.type = tmp.first().toLowerCase() + tmp.substring(1)
                        break
                    }
                }
            }
            return if (types != null) {
                var collection = wrappy.collName
                try {
                    collection = config.findObjectAttribute<String>("mongo", wrappy.clazz.simpleName)
                }
                catch (ex: Exception) {
                    wrappy.log.warning("No collection configured for Repository ${wrappy.clazz.simpleName}. Default database will be used.")
                    wrappy.log.warning(" If you want to specify one, add ${wrappy.clazz.simpleName} = \"[collection_name]\" to your config.toml in the mongo-section.")
                }
                wrappy.collection = wrappy.db.getCollection<Any>(collection, types.first() as Class<Any>)
                true
            } else false
        }
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        if (method != null) {
            // Query
            if (method.name.startsWith("find") ) {
                if (method.name == "findAll" && (args == null || args.isEmpty())) {
                    try {
                        val a = collection.find()
                        val list = mutableListOf<Any>()
                        a.forEach {
                            list.add(it)
                        }
                        return if (list.isEmpty()) {
                            null
                        } else list
                    }
                    catch (ex: Exception) {
                        log.warning(ExceptionsTools.exceptionToString(ex))
                    }
                }
                else if (args != null && args.size == 1 && args.first() is String) {
                    val attribute = method.name.substring(6,7).toLowerCase() + method.name.substring(7)
                    val a = collection.find(eq(attribute, args.first()))
                    val list = mutableListOf<Any>()
                    a.forEach {
                        list.add(it)
                    }
                    return if (list.isEmpty()) {
                        null
                    } else list
                }
                // TODO MongoWrapper streamline And
                else if (args != null && args.size >= 2 && method.name.contains("And")) {
                    val tmp = (method.name.substring(6,7).toLowerCase() + method.name.substring(7)).split("And")
                    val attributes = mutableListOf<String>()
                    for (attrib in tmp) {
                        attributes.add(attrib.first().toLowerCase() + attrib.substring(1))
                    }
                    val filter = mutableListOf<Bson>()
                    attributes.forEachIndexed { index, s ->
                        filter.add( eq(s, args[index]))
                    }
                    val a = collection.find(and(filter))
                    val list = mutableListOf<Any>()
                    a.forEach {
                        list.add(it)
                    }
                    return if (list.isEmpty()) {
                        null
                    } else list
                }
                return null
            // Insert
            } else if (method.name.startsWith("insert")) {
                if (method.name == "insert" && args != null && args.size == 1) {
                    if (args.first()::class == types.first.kotlin) {
                        try {
                            collection.insertOne(args.first())
                            return null
                        } catch (ex: Exception) {
                            log.warning("Cannot add object ${args.first()} to database. Reason:")
                            log.warning(ExceptionsTools.exceptionToString(ex))
                            log.warning("Did you initalize all properties of the object?")
                        }
                    }

                }
            // Update TODO test new vararg update
            } else if (method.name.startsWith("update")) {
                if (method.name == "updateAll" && args != null && args.size == 2) {
                    //val update = args.slice(2 until args.size)
                    val eqRaw = (args[0] as MongoRepo.Equals).eq
                    val setRaw = (args[1] as MongoRepo.Set).set
                    val eq = mutableListOf<Bson>()
                    val set = mutableListOf<Bson>()
                    for (el in eqRaw) {
                        eq.add(eq(el.first as String, el.second))
                    }
                    for (el in setRaw) {
                        set.add(set(el.first as String, el.second))
                    }
                    collection.updateMany(combine(eq), combine(set))
                    return null


                }
            // Delete
            } else if (method.name.startsWith("delete")) {
                if (method.name == "deleteAll" && args != null && args.size == 1) {
                    val eqRaw = (args[0] as MongoRepo.Equals).eq
                    val eq = mutableListOf<Bson>()
                    for (el in eqRaw) {
                        eq.add(eq(el.first as String, el.second))
                    }
                    collection.deleteMany(combine(eq))
                    return null
                }
            // Count
            } else if (method.name.startsWith("count")) {
                if (method.name == "countAll" && (args == null || args.isEmpty())) {
                    return collection.countDocuments()
                }
            }
        }
        return null
    }
}