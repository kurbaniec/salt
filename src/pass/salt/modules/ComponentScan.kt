package pass.salt.modules

import pass.salt.annotations.Scan
import pass.salt.container.Container
import pass.salt.loader.config.Config


class ComponentScan(
        val config: Config,
        val container: Container
): SaltProcessor {

    override fun process(className: String) {
        val cls = SaltProcessor.processClass<Scan>(className)
        if (cls != null) {
            container.addElement(className)
        }
    }

    override fun shutdown() {

    }

}