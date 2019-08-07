package pass.salt.annotations.modules

import pass.salt.annotations.Scan
import pass.salt.container.Container
import pass.salt.loader.config.Config


class ComponentScan(
        val className: String,
        val config: Config,
        val container: Container
): AnnotationProcessor {

    override fun process() {
        val cls = AnnotationProcessor.processClass<Scan>(className)
        if (cls != null) {
            container.addElement(className)
        }
    }

}