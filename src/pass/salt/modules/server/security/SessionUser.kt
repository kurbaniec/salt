package pass.salt.modules.server.security

class SessionUser() {
    lateinit var username: String

    constructor(username: String) : this() {
        this.username = username
    }
}