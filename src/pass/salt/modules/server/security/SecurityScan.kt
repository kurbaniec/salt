package pass.salt.modules.server.security

import pass.salt.annotations.WebSecurity
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor

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