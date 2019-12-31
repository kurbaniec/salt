package pass.salt.code.modules

import java.util.logging.Logger

/**
 * Dummy class that is created when an unknown module is requested.
 */
class ModuleNotFound : SaltProcessor {
    val logger: Logger = Logger.getLogger("SaltLogger")

    /**
     * Log warning that given module is not known.
     */
    override fun process(className: String) {
        logger.warning("Module not found...")
    }

    /**
     * Not used.
     */
    override fun shutdown() {

    }
}