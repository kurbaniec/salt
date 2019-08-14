package pass.dev.server

import pass.salt.annotations.WebSecurity
import pass.salt.modules.server.security.WebSecurityConfig
import pass.salt.modules.server.security.WebSecurityConfigurator

@WebSecurity
class SecTest: WebSecurityConfigurator {
    override fun authenticate(username: String, password: String): Boolean {
        return (username == "admin" && password == "admin")
    }

    override fun configure(conf: WebSecurityConfig) {
        conf
            .matchRequests("/").permitAll()
            .anyRequest().authenticated()
            .loginPath("/login")
    }
}