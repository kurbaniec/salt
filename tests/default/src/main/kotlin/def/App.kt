package def

import pass.salt.code.SaltApplication

/**
 *
 *
 * @author Kacper Urbaniec
 * @version 2019-11-18
 */
/**
 * Define new Salt Application.
 */
class App {
    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val app = SaltApplication()
        }

    }
}