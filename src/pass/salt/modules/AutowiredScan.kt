package pass.salt.modules

import pass.salt.annotations.Autowired
import pass.salt.container.Container
import kotlin.reflect.KFunction

class AutowiredScan(val container: Container): SaltProcessor {
    override fun process(className: String) {
        val props = SaltProcessor.processProp<Autowired>(className)
        if (props != null) {
            val instace = container.getElement(className)
            for (p in props) {
                var newVal = container.getElement(p.name)
                if (newVal == null) {
                    newVal = container.getElement(p.returnType.toString())
                }
                if (newVal != null && instace != null) {
                    p.setter.call(instace, newVal)
                }
            }
        }
    }

    override fun shutdown() {

    }


}