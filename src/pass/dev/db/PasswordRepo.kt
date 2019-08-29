package pass.dev.db

import pass.salt.annotations.MongoDB
import pass.salt.modules.db.mongo.MongoRepo

@MongoDB
interface PasswordRepo: MongoRepo<Password, String> {

    fun findByUserid(userid: String): List<Password>?

    fun findByUseridAndName(userid: String, name: String): List<Password>?

    fun findByUseridAndNameAndOrganization(userid: String, name: String, organization: String): List<Password>?

    fun findByNameAndOrganizationAndPasswordAndLink(name: String, organization: String, password: String, link: String): List<Password>?


}