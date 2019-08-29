package pass.salt.modules

import java.util.logging.Logger

class ModuleNotFound : SaltProcessor {
    val logger: Logger = Logger.getLogger("weserver")
    override fun process(className: String) {
        logger.warning("Module not found...")
    }

    override fun shutdown() {

    }
}