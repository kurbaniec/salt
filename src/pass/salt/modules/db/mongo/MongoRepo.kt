package pass.salt.modules.db.mongo

interface MongoRepo<T, I> {
    class Equals(vararg val eq: Pair<String, Any>)
    class Set(vararg val set: Pair<String, Any>)

    fun findAll(): ArrayList<T>?

    fun insert(data: T)

    fun countAll(): Long

    //fun updateAll(field: String, match: Any, vararg update: Pair<String, Any>)
    fun updateAll(fields: Equals, update: Set)

    fun deleteAll(field: Equals)

}