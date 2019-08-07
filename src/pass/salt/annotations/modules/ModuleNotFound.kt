package pass.salt.annotations.modules

import java.util.logging.Logger

class ModuleNotFound() : AnnotationProcessor {
    val logger: Logger = Logger.getLogger("weserver")
    override fun process() {
        logger.warning("Module not found...")
    }

}