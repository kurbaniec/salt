package pass.dev.test.db

import pass.salt.annotations.MongoDB
import pass.salt.modules.db.mongo.MongoRepo

@MongoDB
interface TestRepo: MongoRepo<Testo, String> {

    fun findByTest(test: String): List<Testo>?

}