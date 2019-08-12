package pass.dev.server

import pass.salt.annotations.Autowired
import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Scan
import pass.salt.modules.server.webparse.Model

@Controller
class Test2 {

    @Autowired
    lateinit var test: Test

    @Get("/testo")
    fun test2(m: Model): String {
        return "testo"
    }
}