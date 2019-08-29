package pass.dev.test.db

import pass.salt.annotations.MongoDB
import pass.salt.modules.db.mongo.MongoRepo

@MongoDB
interface UserTestoRepo: MongoRepo<UserTesto, String> {

    fun findByUsername(username: String): List<UserTesto>?

}