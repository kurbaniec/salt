package pass.dev.server

import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Param

@Controller
class Test {
    @Get("/")
    fun test(@Param("id") id: String): String {
        return "login"
    }
}