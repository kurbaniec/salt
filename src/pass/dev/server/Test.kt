package pass.dev.server

import pass.dev.test.db.TestRepo
import pass.dev.test.db.UserTesto
import pass.dev.test.db.UserTestoRepo
import pass.salt.annotations.Autowired
import pass.salt.annotations.Controller
import pass.salt.annotations.Get
import pass.salt.modules.db.mongo.MongoRepo
import pass.salt.modules.server.security.SessionUser
import pass.salt.modules.server.webparse.Model

@Controller
class Test {
    @Autowired
    lateinit var userTestoRepo: UserTestoRepo

    @Autowired
    lateinit var testRepo: TestRepo

    /**
    @Get("/")
    fun test(): String {
        //testDB()
        return "hello"
    }

    @Get("/login")
    fun login(m: Model): String {
        return "login"
    }

    @Get("/index")
    fun passwdIndex(m: Model, s: SessionUser): String {
        return "index"
    }*/


    fun testDB() {
        val a = userTestoRepo.findAll()
        userTestoRepo.insert(UserTesto("1", "baum", "3"))
        val b = userTestoRepo.findByUsername("baum")
        userTestoRepo.updateAll(MongoRepo.Equals(Pair("username", "baum")), MongoRepo.Set(Pair("username", "dada")))
        val d = userTestoRepo.findAll()
        //userTestoRepo.deleteAll("username", "dada")
        val e = userTestoRepo.findAll()
        val c = userTestoRepo.countAll()
        val a2 = testRepo.findAll()
        val b2 = testRepo.findByTest("tt")
        val c2 = testRepo.countAll()
    }
}