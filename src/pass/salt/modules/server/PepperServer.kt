package pass.salt.modules.server

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool

class PepperServer(
        val config: Config,
        val container: Container
): SaltProcessor {
    override fun process(className: String) {
        val executor = container.getElement("saltThreadPool") as SaltThreadPool
        val port = config.findObjectAttribute("server", "port") as Int
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}