package pass.dev.server

import pass.dev.db.UserRepo
import pass.salt.annotations.Autowired
import pass.salt.annotations.WebSecurity
import pass.salt.modules.server.security.WebSecurityConfig
import pass.salt.modules.server.security.WebSecurityConfigurator

@WebSecurity
class SecTest: WebSecurityConfigurator {

    @Autowired
    lateinit var userRepo: UserRepo

    override fun authenticate(username: String, password: String): Boolean {
        val result = userRepo.findByUsernameAndPassword(username, password)
        return result != null
        //return (username == "admin" && password == "admin")
    }

    override fun configure(conf: WebSecurityConfig) {
        conf
            .matchRequests("/hello", "/css/login.css", "/favicon.ico", "/register").permitAll()
            .anyRequest().authenticated()
            .loginPath("/login")
            .logoutPath("/logout")
            .successfulLoginPath("/")
    }
}