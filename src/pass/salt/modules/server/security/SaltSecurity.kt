package pass.salt.modules.server.security

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import java.util.*
import kotlin.reflect.KFunction

class SaltSecurity(
    val config: Config,
    val container: Container
): SaltProcessor {
    var open = false
    var login = "/login"
    val mapping = mutableListOf<String>()
    val sessions = mutableMapOf<String, SessionUser>()
    lateinit var auth: Pair<Any, KFunction<*>>


    override fun process(className: String) {
        if (config.findObjectAttribute("security", "enable") as Boolean) {
            container.addElement("saltSecurity", this)
        }
    }

    fun setConfiguration(conf: WebSecurityConfig, auth: Pair<Any, KFunction<*>>) {
        this.auth = auth
        open = conf.open
        login = conf.login
        mapping.addAll(conf.mapping)
        if (open) {
            mapping.add(login)
        }
        // TODO add mapping exceptions through config for files like favicon.ico
    }

    fun addSession(username: String): String {
        val nameSpace = ('0'..'z').toList().toTypedArray()
        val sessionID = (1..101).map { nameSpace.random() }.joinToString("").replace(";", "/")
        sessions[sessionID] = SessionUser(username)
        return sessionID
    }

    fun isValidSession() {

    }

    fun checkAuthorization(basicAuth: String): Pair<Boolean, String> {
        val enc = if (basicAuth.contains(" ")) {
            val tmp = basicAuth.trim().split(" ")
            tmp[1]
        }
        else basicAuth
        val dec = String(Base64.getDecoder().decode(enc))
        val tmp = dec.split(":")
        val username = tmp[0]
        val password = tmp[1]
        return Pair(auth.invoke(username, password) as Boolean, username)
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

}