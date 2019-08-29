package pass.dev.db

class User() {
    // lateinit var id: String <- Id of mongo, no dupplicates allowed!
    lateinit var myid: String
    lateinit var username: String
    lateinit var password: String

    constructor(myid: String, username: String, password: String): this() {
        this.myid = myid
        this.username = username
        this.password = password
    }
}