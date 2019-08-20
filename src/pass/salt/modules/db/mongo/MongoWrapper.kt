package pass.salt.modules.db.mongo

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.W



class MongoWrapper: InvocationHandler {
    lateinit var clazz: Class<*>

    companion object {
        fun  <W>getWrapper(cls: Class<W>): W {
            val wrappy = MongoWrapper()
            wrappy.clazz = cls
            val proxy = Proxy.newProxyInstance(cls.classLoader, arrayOf(cls), wrappy) as W
            return proxy
        }
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        if (method != null) {
            if (method.getName().startsWith("get") && (args == null || args.isEmpty())) {
                return "Baum";
            }
        }
        return ""
    }
}