package pass.salt.code.modules.server.security

import pass.salt.code.annotations.WebSecurity
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor

class SecurityScan(
    val config: Config,
    val container: Container
): SaltProcessor {
    var enabled = false
    var configured = false

    init {
        if (config.findObjectAttribute("security", "enable") as Boolean) {
            enabled = true
        }
    }

    override fun process(className: String) {
        if (enabled) {
            if (!configured) {
                val valid = SaltProcessor.processClass<WebSecurity>(className)
                if (valid != null) {
                    val instance = container.getElement(className)
                    if (instance is WebSecurityConfigurator) {
                        val conf = WebSecurityConfig()
                        instance.configure(conf)
                        val security = container.getElement("saltSecurity")
                        if (security != null && security is SaltSecurity) {
                            security.setConfiguration(conf, Pair(instance, instance::authenticate))
                            configured = true
                        }
                    }
                }
            }
        }
    }

    override fun shutdown() {

    }
}