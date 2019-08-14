package pass.salt

import pass.salt.container.Container
import pass.salt.loader.Loader
import pass.salt.loader.config.Config

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