package pass.dev.db

import pass.salt.annotations.MongoDB
import pass.salt.modules.db.mongo.MongoRepo

@MongoDB
interface UserRepo: MongoRepo<User, String> {

    fun findByUsername(username: String): List<User>?

    fun findByUsernameAndPassword(username: String, password: String): List<User>?

    fun findByMyidAndUsernameAndPassword(myid: String, username: String, password: String): List<User>?

    fun findByMyid(myid: String): List<User>?

}