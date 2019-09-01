package pass.salt.modules.server.mapping

import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.annotations.Post
import pass.salt.container.Container
import pass.salt.loader.config.Config
import pass.salt.modules.SaltProcessor
import pass.salt.modules.server.PepperServer
import java.util.logging.Logger
import kotlin.reflect.KFunction

class MappingScan(
    val config: Config,
    val container: Container
): SaltProcessor {
    override fun process(className: String) {
        //val server = container.getElement("serverMainThread") as ServerMainThread
        log.info("mapping_init: " + className)
        val server = container.getElement("pepperServer") as PepperServer
        val get = SaltProcessor.processClassFunc<Controller, Get>(className)
        val post = SaltProcessor.processClassFunc<Controller, Post>(className)
        log.info("MappingScan: Classname:" + className)
        if (get != null) addMapping(className, get, server)
        if (post != null) addMapping(className, post, server)
    }

    fun addMapping(className: String, data: MutableList<Pair<Annotation, KFunction<*>>>, server: PepperServer) {
        val log = Logger.getGlobal()
        log.info("1")
        val instance = container.getElement(className)
        if (instance != null) {
            for (func in data) {
                log.info("2$instance")
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