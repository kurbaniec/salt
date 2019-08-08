package pass.salt.modules.server

import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Post
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.SaltThreadPool
import java.net.ServerSocket
import kotlin.reflect.KFunction

class MappingScan(
    val config: Config,
    val container: Container
): SaltProcessor {
    override fun process(className: String) {
        val server = container.getElement("serverMainThread") as ServerMainThread
        val get = SaltProcessor.processClassFunc<Controller, Get>(className)
        val post = SaltProcessor.processClassFunc<Controller, Post>(className)
        if (get != null) addMapping(className, get, server)
        if (post != null) addMapping(className, post, server)
    }

    fun addMapping(className: String, data: MutableList<Pair<Annotation, KFunction<*>>>, server: ServerMainThread) {
        val instance = container.getElement(className)
        if (instance != null) {
            for (func in data) {
                if (func.first is Get) {
                    server.addGetMapping((func.first as Get).path, Pair(instance, func.second))
                }
                else if (func.first is Post) {
                    server.addGetMapping((func.first as Post).path, Pair(instance, func.second))
                }
            }
        }
    }

    override fun shutdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}