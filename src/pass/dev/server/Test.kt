package pass.dev.server

import pass.salt.loader.annotations.Get

class Test {
    @Get("/")
    fun test(): String {
        return "hello"
    }
}