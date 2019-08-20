package pass.salt.modules.db.mongo

interface MongoRepo<T, I> {

    fun findAll(): ArrayList<T>

}