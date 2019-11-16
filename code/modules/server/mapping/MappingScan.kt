package pass.salt.code.modules.server.mapping

import pass.salt.code.annotations.Controller
import pass.salt.code.annotations.Get
import pass.salt.code.annotations.Post
import pass.salt.code.container.Container
import pass.salt.code.loader.config.Config
import pass.salt.code.modules.SaltProcessor
import pass.salt.code.modules.server.PepperServer
import kotlin.reflect.KFunction

class MappingScan(
    val config: Config,
    val container: Container
): SaltProcessor {
    override fun process(className: String) {
        //val server = container.getElement("serverMainThread") as ServerMainThread
        val server = container.getElement("pepperServer") as PepperServer
        val get = SaltProcessor.processClassFunc<Controller, Get>(className)
        val post = SaltProcessor.processClassFunc<Controller, Post>(className)
        if (get != null) addMapping(className, get, server)
        if (post != null) addMapping(className, post, server)
    }

    fun addMapping(className: String, data: MutableList<Pair<Annotation, KFunction<*>>>, server: PepperServer) {
        val instance = container.getElement(className)
        if (instance != null) {
            for (func in data) {
                if (func.first is Get) {
                    server.addGetMapping((func.first as Get).path, Pair(instance, func.second))
                }
                else if (func.first is Post) {
                    server.addPostMapping((func.first as Post).path, Pair(instance, func.second))
                }
            }
        }
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}