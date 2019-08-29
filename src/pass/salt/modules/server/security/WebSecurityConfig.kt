package pass.salt.modules.server.security

import pass.salt.exceptions.InvalidSecurityConfigurationException

interface WebSecurityConfigurator {
    fun configure(conf: WebSecurityConfig)
    fun authenticate(username: String, password: String): Boolean
}

class WebSecurityConfig {
    private var set = false
    private var matchBurn = false
    private var anyBurn = false
    private var allBurn = false
    private var authBurn = false
    var open = false
        private set
    var mapping = mutableListOf<String>()
    var login = ""
    var logout = "/logout"
    var success = "/"

    fun matchRequests(vararg paths: String): WebSecurityConfig {
        if (!matchBurn) {
            matchBurn = true
            for (path in paths) {
                mapping.add(path)
            }
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    fun anyRequest(): WebSecurityConfig {
        if (!anyBurn) {
            anyBurn = true
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    fun permitAll(): WebSecurityConfig {
        if (!allBurn) {
            allBurn = true
            setMethod()
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    fun authenticated(): WebSecurityConfig {
        if (!authBurn) {
            authBurn = true
            setMethod()
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    fun loginPath(path: String): WebSecurityConfig {
        login = path
        return this
    }

    fun logoutPath(path: String): WebSecurityConfig {
        logout = path
        return this
    }

    fun successfulLoginPath(path: String): WebSecurityConfig {
        success = path
        return this
    }

    private fun setMethod() {
        if (!set) {
            set = true
            if (anyBurn) open = true
        }
    }
}