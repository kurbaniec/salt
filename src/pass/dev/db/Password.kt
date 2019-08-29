package pass.dev.db

class Password() {
    lateinit var userid: String
    lateinit var name: String
    lateinit var organization: String
    lateinit var password: String
    lateinit var link: String

    constructor(userid: String, name: String, organization: String, password: String, tag: String): this() {
        this.userid = userid
        this.name = name
        this.organization = organization
        this.password = password
        this.link = tag
    }
}