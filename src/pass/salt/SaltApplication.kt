package pass.salt

import pass.salt.loader.Loader

class SaltApplication() {
    val loader: Loader
    init {
        loader = Loader()
    }
}