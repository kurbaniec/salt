package pass.salt.modules.server.security

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
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
    }

    fun isValidSession() {

    }

    override fun shutdown() {

    }

    private fun Pair<Any, KFunction<*>>.call(vararg args: Any?): Any? {
        val (instance, func) = this
        return func.call(instance, *args)
    }

}