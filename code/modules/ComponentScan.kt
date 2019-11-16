package pass.salt.code.modules

import pass.salt.code.annotations.Scan
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config


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