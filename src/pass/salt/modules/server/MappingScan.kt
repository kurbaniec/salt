package pass.salt.modules.server

import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool
import java.net.ServerSocket

class MappingScan(
    val config: Config,
    val container: Container
): SaltProcessor {
    override fun process(className: String) {
        val server = container.getElement("serverMainThread") as ServerMainThread


    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}