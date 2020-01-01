package pass.salt.code.modules.server.security

import pass.salt.code.exceptions.InvalidSecurityConfigurationException
import pass.salt.code.annotations.WebSecurity


/**
 * Interface that every class marked with [WebSecurity] should implement to configure Salt security.
 *
 * Example usage:
 *
 * ```kotlin
 * @WebSecurity
 * class SecTest: WebSecurityConfigurator {
 *
 *     override fun authenticate(username: String, password: String): Boolean {
 *         return username == "admin" && password == "admin"
 *     }
 *
 *     override fun configure(conf: WebSecurityConfig) {
 *         conf
 *             .matchRequests("/hello", "/css/login.css", "/favicon.ico", "/register").permitAll()
 *             .anyRequest().authenticated()
 *             .loginPath("/login")
 *             .logoutPath("/logout")
 *             .successfulLoginPath("/")
 *     }
 * }
 * ```
 */
interface WebSecurityConfigurator {
    /**
     * Configures handling of request mappings.
     */
    fun configure(conf: WebSecurityConfig)

    /**
     * Configures authentication process.
     */
    fun authenticate(username: String, password: String): Boolean
}

/**
 * Configures authentication on request paths. Also configures login/logout and success path.
 *
 * If [permitAll] is called on a [matchRequests], the configuration is open, only mapped entries are secured.
 *
 * If [authenticated] is called on a [matchRequests], the configuration is not open, mapped entries will not secured
 * but every other request.
 */
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


    /**
     * Used to select multiple request path manually.
     */
    fun matchRequests(vararg paths: String): WebSecurityConfig {
        if (!matchBurn) {
            matchBurn = true
            for (path in paths) {
                mapping.add(path)
            }
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    /**
     * Used to select any request path beside mapped ones.
     */
    fun anyRequest(): WebSecurityConfig {
        if (!anyBurn) {
            anyBurn = true
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    /**
     * Permit access on selected request paths.
     */
    fun permitAll(): WebSecurityConfig {
        if (!allBurn) {
            allBurn = true
            setMethod()
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    /**
     * Permit only authenticated access on selected request paths.
     */
    fun authenticated(): WebSecurityConfig {
        if (!authBurn) {
            authBurn = true
            setMethod()
        } else throw InvalidSecurityConfigurationException("Called function a second time, which is illegal.")
        return this
    }

    /**
     * Configure login request path.
     */
    fun loginPath(path: String): WebSecurityConfig {
        login = path
        return this
    }

    /**
     * Configure logout request path.
     */
    fun logoutPath(path: String): WebSecurityConfig {
        logout = path
        return this
    }

    /**
     * Configure request path the client will be redirected when successfully loggend in.
     */
    fun successfulLoginPath(path: String): WebSecurityConfig {
        success = path
        return this
    }

    /**
     * Toggles security mode - open or not open.
     */
    private fun setMethod() {
        if (!set) {
            set = true
            if (anyBurn) open = true
        }
    }
}