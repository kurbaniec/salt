package pass.salt.code

import pass.salt.code.container.Container
import pass.salt.code.loader.Loader
import pass.salt.code.loader.config.Config

/**
 * Initializes a new application that utilises the Salt framework.
 */
class SaltApplication() {
    val loader: Loader


    init {
        loader = Loader()
        config = loader.config
        container = loader.container

    }

    companion object {
        lateinit var config: Config
        lateinit var container: Container
    }
}