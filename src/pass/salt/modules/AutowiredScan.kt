package pass.salt.modules

import pass.salt.annotations.Autowired
import pass.salt.container.Container
import kotlin.reflect.KFunction

class AutowiredScan(val container: Container): SaltProcessor {
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

    override fun shutdown() {

    }


}