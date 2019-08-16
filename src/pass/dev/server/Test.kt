package pass.dev.server

import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Param
import pass.salt.modules.server.webparse.Model

@Controller
class Test {
    @Get("/")
    fun test(): String {
        return "hello"
    }

    @Get("/login")
    fun login(m: Model): String {
        return "login"
    }
}