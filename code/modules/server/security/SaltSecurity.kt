package pass.salt.code.modules.server.security

import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.reflect.KFunction

class SaltSecurity(
    val config: Config,
    val container: Container
): SaltProcessor {
    var open = false
    var login = "/login"
    var logout = "/logout"
    var success = "/"
    val mapping = mutableListOf<String>()
    val sessions = mutableMapOf<String, SessionUser>()
    var timeout: Int = 15
    var passwdTimeout: Int = 5
    var passwdFails: Int = 3
    val sessionTimeout = mutableMapOf<String, Timeout>()
    lateinit var auth: Pair<Any, KFunction<*>>

    override fun process(className: String) {
        if (config.findObjectAttribute<Boolean>("security", "enable")) {
            timeout = config.findObjectAttribute<Int>("security", "timeout")
            passwdTimeout = config.findObjectAttribute<Int>("security", "password_timeout")
            passwdFails = config.findObjectAttribute<Int>("security", "password_fails")
            container.addElement("saltSecurity", this)
        }
    }

    fun setConfiguration(conf: WebSecurityConfig, auth: Pair<Any, KFunction<*>>) {
        this.auth = auth
        open = conf.open
        login = conf.login
        logout = conf.logout
        success = conf.success
        mapping.addAll(conf.mapping)
        if (open) {
            mapping.add(login)
            mapping.add(logout)
        }
    }

    fun addSession(username: String): String {
        val nameSpace = ('0'..'z').toList().toTypedArray()
        val sessionID = (1..101).map { nameSpace.random() }.joinToString("").replace(";", "/")
        sessions[sessionID] = SessionUser(username, sessionID, this)
        return sessionID
    }

    fun removeSession(sid: String) {
        sessions.remove(sid)
    }

    fun isValidSession(sid: String): Boolean {
        if (sessions.containsKey(sid)) {
            val user = sessions[sid]
            if (user != null) {
                val mi = Calendar.getInstance()
                if (user.init.plus(Duration.of(timeout.toLong(), ChronoUnit.MINUTES)) > LocalDateTime.now()) {
                    user.init = LocalDateTime.now()
                    return true
                }
                else {
                    sessions.remove(sid)
                }
            }
        }
       return false
    }

    fun getSession(sid: String): SessionUser? {
        return sessions[sid]
    }

    fun checkAuthorization(basicAuth: String): Pair<Int, String> {
        val enc = if (basicAuth.contains(" ")) {
            val tmp = basicAuth.trim().split(" ")
            tmp[1]
        }
        else basicAuth
        val dec = String(Base64.getDecoder().decode(enc))
        val tmp = dec.split(":")
        val username = tmp[0]
        val password = tmp[1]
        val check = Pair(auth.invoke(username, password) as Boolean, username)
        val to = sessionTimeout[username]
        if (check.first) {
            return if (to == null) Pair(0, username)
            else {
                if (to.stopInit == null || (to.stopInit != null &&
                    to.stopInit!!.plus(Duration.of(passwdTimeout.toLong(), ChronoUnit.MINUTES)) < LocalDateTime.now())) {
                    to.clear()
                    Pair(0, username)
                } else Pair(2, username)
            }
        } else {
            if (to == null) {
                val newTo = Timeout(passwdTimeout, passwdFails)
                newTo.addStrike()
                sessionTimeout[username] = newTo
                return Pair(1, username)
            } else {
                if (to.stopInit == null || (to.stopInit != null &&
                    to.stopInit!!.plus(Duration.of(passwdTimeout.toLong(), ChronoUnit.MINUTES)) < LocalDateTime.now())) {
                    to.addStrike()
                    val stop = to.process()
                    return if (!stop) Pair(1, username) else Pair(2, username)
                }
                return Pair(2, username)
            }
        }
    }

    override fun shutdown() {

    }

    private fun Pair<Any, KFunction<*>>.invoke(vararg args: Any): Any? {
        val list = mutableListOf<Any>()
        for (arg in args) {
            list.add(arg)
        }
        return auth.second.call(*list.toTypedArray())
    }

    class Timeout(val timeout: Int, val fails: Int) {
        val mapping = mutableListOf<LocalDateTime>()
        var stopInit: LocalDateTime? = null

        fun process(): Boolean {
            val now = LocalDateTime.now()
            val remove = mutableListOf<LocalDateTime>()
            for (el in mapping) {
                if (el.plus(Duration.of(timeout.toLong(), ChronoUnit.MINUTES)) < now) {
                    remove.add(el)
                }
            }
            for (el in remove) mapping.remove(el)
            return if(mapping.size >= fails) {
                stopInit = LocalDateTime.now()
                true
            } else false
        }

        fun addStrike() = mapping.add(LocalDateTime.now())

        fun clear() = mapping.clear()
    }

}