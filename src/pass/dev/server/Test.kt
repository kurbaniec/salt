package pass.dev.server

import pass.dev.db.UserRepo
import pass.salt.annotations.Autowired
import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Param
import pass.salt.modules.server.security.SessionUser
import pass.salt.modules.server.webparse.Model

@Controller
class Test {
    @Autowired
    lateinit var userRepo: UserRepo

    @Get("/")
    fun test(): String {
        return "hello"
    }

    @Get("/login")
    fun login(m: Model): String {
        return "login"
    }

    @Get("/index")
    fun passwdIndex(m: Model, s: SessionUser): String {
        return "index"
    }
}