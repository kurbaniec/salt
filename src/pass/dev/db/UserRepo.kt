package pass.dev.db

import pass.salt.annotations.MongoDB
import pass.salt.modules.db.mongo.MongoRepo

@MongoDB
interface UserRepo: MongoRepo<User, String> {

    fun findByFirstname(firstname: String): User

}