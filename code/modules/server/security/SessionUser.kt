package pass.salt.code.modules.server.security

import java.time.LocalDateTime

/**
 * Contains the client information for the session at runtime.
 */
class SessionUser() {
    lateinit var username: String
    private lateinit var sid: String
    private lateinit var sec: SaltSecurity
    var init = LocalDateTime.now()

    constructor(username: String, sid: String, sec: SaltSecurity) : this() {
        this.username = username
        this.sid = sid
        this.sec = sec
    }

    /**
     * End client session.
     */
    fun endSession() {
        sec.removeSession(sid)
    }
}