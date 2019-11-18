package def.dev

import pass.salt.code.annotations.Controller
import pass.salt.code.annotations.Get

/**
 *
 *
 * @author Kacper Urbaniec
 * @version 2019-11-18
 */
@Controller
class Controller {
    
    @Get("/")
    fun index(): String {
        return "index"
    }
}