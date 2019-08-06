package pass.dev.server

import pass.salt.loader.annotations.Controller
import pass.salt.loader.annotations.Get

@Controller
class Test {
    @Get("/")
    fun test(): String {
        return "hello"
    }
}