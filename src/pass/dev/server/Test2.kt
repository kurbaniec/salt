package pass.dev.server

import pass.salt.annotations.Autowired
import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Scan
import pass.salt.modules.server.security.SessionUser
import pass.salt.modules.server.webparse.Model

@Controller
class Test2 {

    @Autowired
    lateinit var test: Test

    @Get("/testo")
    fun test2(m: Model, s: SessionUser): String {
        val b = s
        return "testo"
    }

    data class User(val login: String, val name: String, val address: String)
    @Get("/parser")
    fun parser(m: Model): String {
        val msg = "baum"
        m.addAttribute("message", msg)
        val user = listOf<User>(User("baum@baum", "baumann", "baugasse"),
                User("baum2@baum", "baumann2", "baugasse2"))
        m.addAttribute("users", user)
        m.addAttribute("testo", "testo")
        return "parser"
    }
}