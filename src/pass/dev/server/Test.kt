package pass.dev.server

import pass.salt.annotations.Controller
import pass.salt.annotations.Get

@Controller
class Test {
    @Get("/")
    fun test(): String {
        return "login"
    }
}