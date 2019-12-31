package pass.salt.code.modules.db.mongo

/**
 * Defines basic CRUD-operations to interact with MongoDB.
 */
interface MongoRepo<T, I> {
    class Equals(vararg val eq: Pair<String, Any>)
    class Set(vararg val set: Pair<String, Any>)

    fun findAll(): ArrayList<T>?

    fun insert(data: T)

    fun countAll(): Long

    fun updateAll(fields: Equals, update: Set)

    fun deleteAll(field: Equals)

}