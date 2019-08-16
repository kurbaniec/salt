package pass.salt.modules.server.security

import java.time.LocalDateTime
import java.util.*

class SessionUser() {
    lateinit var username: String
    var init = LocalDateTime.now()

    constructor(username: String) : this() {
        this.username = username
    }
}