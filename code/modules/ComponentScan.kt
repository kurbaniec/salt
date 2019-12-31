package pass.salt.code.modules

import pass.salt.code.annotations.Scan
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config

/**
 * Scans classes and adds them to the [Container] if they contain the [Scan] annotation.
 */
class ComponentScan(
        val config: Config,
        val container: Container
): SaltProcessor {

    /**
     * Scans a given class and adds it to the [Container] if it contains the [Scan] annotation.
     */
    override fun process(className: String) {
        val cls = SaltProcessor.processClass<Scan>(className)
        if (cls != null) {
            container.addElement(className)
        }
    }

    /**
     * Not used.
     */
    override fun shutdown() {

    }

}