package pass.salt.code.modules

import pass.salt.code.annotations.Autowired
import pass.salt.code.container.Container

/**
 * Scans classes and injects values to the class properties from the [Container] if the property contains the
 * [Autowired] annotation.
 */
class AutowiredScan(val container: Container): SaltProcessor {

    /**
     * Scans a given class and injects values to the class properties from the [Container] if the property contains the
     * [Autowired] annotation.
     */
    override fun process(className: String) {
        val props = SaltProcessor.processProp<Autowired>(className)
        if (props != null) {
            val instance = container.getElement(className)
            for (p in props) {
                var newVal = container.getElement(p.name)
                if (newVal != null && instance != null) {
                    if (newVal.javaClass.kotlin.qualifiedName == p.returnType.toString()) {
                        p.setter.call(instance, newVal)
                    }
                    else {
                        var proxyHasInterface = false
                        for (it in newVal.javaClass.interfaces) {
                            if (it.kotlin.qualifiedName == p.returnType.toString()) {
                                proxyHasInterface = true
                                break
                            }
                        }
                        if (proxyHasInterface) {
                            p.setter.call(instance, newVal)
                        }
                    }
                }
                else {
                    newVal = container.getElement(p.returnType.toString())
                    if (newVal != null && instance != null &&
                            newVal.javaClass.kotlin.qualifiedName == p.returnType.toString()) {
                        p.setter.call(instance, newVal)
                    }
                }
            }
        }
    }

    /**
     * Not used.
     */
    override fun shutdown() {

    }


}