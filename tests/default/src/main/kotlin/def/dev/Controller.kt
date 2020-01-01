package def.dev

import pass.salt.code.annotations.Autowired
import pass.salt.code.annotations.Controller
import pass.salt.code.annotations.Get
import pass.salt.code.annotations.Post
import pass.salt.code.modules.server.HTTPTransport
import pass.salt.code.modules.server.webparse.Model

/**
 * simple controller for testing purposes.
 *
 * @author Kacper Urbaniec
 * @version 2019-11-18
 */
@Controller
class Controller {
    
    @Autowired
    lateinit var autowiredTest: AutowiredTest 
    
    @Get("/")
    fun index(): String {
        return "index"
    }

    @Get("/template")
    fun template(m: Model): String {
        data class User(val id: Int)
        m.addAttribute("message", "Salt")
        m.addAttribute("users", listOf(User(0), User(1)))
        return "template"
    }

    @Post("/api")
    fun api(): HTTPTransport {
        return HTTPTransport(HTTPTransport.Body("Hi", "Salt")).ok()
    }
}