package pass.dev.server

import pass.dev.db.Password
import pass.dev.db.PasswordRepo
import pass.dev.db.User
import pass.dev.db.UserRepo
import pass.dev.test.db.TestRepo
import pass.dev.test.db.UserTesto
import pass.dev.test.db.UserTestoRepo
import pass.salt.annotations.*
import pass.salt.annotations.Controller
import pass.salt.modules.db.mongo.MongoRepo
import pass.salt.modules.server.HTTPTransport
import pass.salt.modules.server.security.SessionUser
import pass.salt.modules.server.webparse.Model

@Controller
class Controller {
    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var passwordRepo: PasswordRepo


    @Get("/login")
    fun login(m: Model): String {
        return "login"
    }

    @Get("/")
    fun passwdIndex(m: Model, sessionUser: SessionUser): String {
        val user = userRepo.findByUsername(sessionUser.username)
        if (user != null && user.size == 1) {
            val userid = user.first().myid
            val passwords = passwordRepo.findByUserid(userid)
            if (passwords != null) {
                m.addAttribute("entries", passwords.sortedWith(compareBy(Password::organization, Password::name)))
            }
        }
        return "index"
    }

    @Post("/register")
    fun registerUser(@Param("username") username: String, @Param("password") password: String): HTTPTransport {
        val test = userRepo.findByUsername(username)
        return if (test == null) {
            var setPassword = false
            do {
                val nameSpace = ('0'..'9').toList().toTypedArray()
                val myid = (1..101).map { nameSpace.random() }.joinToString("")
                if (userRepo.findByMyid(myid) == null) {
                    userRepo.insert(User(myid, username, password))
                    setPassword = true
                }
            } while (!setPassword)
            HTTPTransport().ok()
        }
        else HTTPTransport().forbidden()
    }

    @Post("/addentry")
    fun addEntry(@Param("name") name: String, @Param("password") password: String,
                 @Param("link") link: String, @Param("organization") organization: String,
                 sessionUser: SessionUser): HTTPTransport {
        var added = false
        val user = userRepo.findByUsername(sessionUser.username)
        if (user != null && user.size == 1) {
            val userid = user.first().myid
            val test = passwordRepo.findByUseridAndNameAndOrganization(userid, name, organization)
            if (test == null) {
                passwordRepo.insert(Password(userid, name, organization, password, link))
                added = true
            }
        }
        return if (added) HTTPTransport().ok() else HTTPTransport().forbidden()
    }

    @Post("/updatepassword")
    fun updatePassword(@Param("name") name: String, @Param("passwd") password: String,
                       @Param("link") link: String, @Param("org") org: String,
                       sessionUser: SessionUser): HTTPTransport {
        val user = userRepo.findByUsername(sessionUser.username)
        if (user != null && user.size == 1) {
            val userid = user.first().myid
            passwordRepo.updateAll(
                    MongoRepo.Equals(Pair("name", name), Pair("userid", userid), Pair("organization", org)),
                    MongoRepo.Set(Pair("password", password), Pair("link", link)))
            val test = passwordRepo.findByNameAndOrganizationAndPasswordAndLink(name, org, password, link)
            return if (test != null) HTTPTransport().ok() else HTTPTransport().forbidden()
        }
        return HTTPTransport().forbidden()
    }

    @Post("/deletepassword")
    fun deletePassword(@Param("name") name: String, @Param("passwd") password: String,
                       @Param("link") link: String, @Param("org") org: String,
                       sessionUser: SessionUser): HTTPTransport {
        val user = userRepo.findByUsername(sessionUser.username)
        if (user != null && user.size == 1) {
            val userid = user.first().myid
            passwordRepo.deleteAll(MongoRepo.Equals(Pair("name", name), Pair("userid", userid), Pair("organization", org)))
            val test = passwordRepo.findByNameAndOrganizationAndPasswordAndLink(name, org, password, link)
            return if (test == null) HTTPTransport().ok() else HTTPTransport().forbidden()
        }
        return HTTPTransport().forbidden()
    }

    @Get("/account")
    fun account(m: Model, sessionUser: SessionUser): String {
        val user = userRepo.findByUsername(sessionUser.username)
        if (user != null && user.size == 1) {
            m.addAttribute("account", user.first())
        }
        return "account"
    }

    @Post("/updateuserpassword")
    fun updateUserPassword(@Param("name") name: String, @Param("passwordOld") passwordOld: String,
                       @Param("passwordNew") passwordNew: String, sessionUser: SessionUser): HTTPTransport {
        val userCheck = userRepo.findByUsername(sessionUser.username)
        if (userCheck != null && userCheck.size == 1) {
            val userid = userCheck.first().myid
            val user = userRepo.findByMyidAndUsernameAndPassword(userid, name, passwordOld)
            return if (user != null && user.size == 1) {
                userRepo.updateAll(
                        MongoRepo.Equals(Pair("username", name), Pair("myid", userid), Pair("password", passwordOld)),
                        MongoRepo.Set(Pair("password", passwordNew))
                )
                val lastCheck = userRepo.findByMyidAndUsernameAndPassword(userid, name, passwordNew)
                if (lastCheck != null) HTTPTransport().ok() else HTTPTransport().forbidden()
            } else HTTPTransport().failedDependecy()
        }
        return HTTPTransport().failedDependecy()
    }

    @Post("/deleteuser")
    fun deleteUser(@Param("name") name: String, sessionUser: SessionUser): HTTPTransport {
        val userCheck = userRepo.findByUsername(sessionUser.username)
        if (userCheck != null && userCheck.size == 1) {
            val userid = userCheck.first().myid
            userRepo.deleteAll(MongoRepo.Equals(Pair("username", name), Pair("myid", userid)))
            val lastCheck = userRepo.findByMyid(userid)
            return if (lastCheck == null) {
                passwordRepo.deleteAll(MongoRepo.Equals(Pair("userid", userid)))
                sessionUser.endSession()
                HTTPTransport().ok()
            } else HTTPTransport().forbidden()
        }
        return HTTPTransport().forbidden()
    }


    @Get("/hello")
    fun test(): String {
        //testDB()
        return "hello"
    }

    @Post("/testpost")
    fun testPost(): HTTPTransport {
        /**return HTTPTransport(HTTPTransport.Header("HTTP/1.1 200 OK",
        "Content-Type: application/x-www-form-urlencoded",
        "Content-Length: 9"),
        HTTPTransport.Body("name=baum"))*/
        return HTTPTransport(HTTPTransport.Body("name=baum")).ok()
    }

    /**
    fun testDB() {
        val a = userTestoRepo.findAll()
        userTestoRepo.insert(UserTesto("1", "baum", "3"))
        val b = userTestoRepo.findByUsername("baum")
        userTestoRepo.updateAll("username", "baum", Pair("username", "dada"))
        val d = userTestoRepo.findAll()
        userTestoRepo.deleteAll("username", "dada")
        val e = userTestoRepo.findAll()
        val c = userTestoRepo.countAll()
        val a2 = testRepo.findAll()
        val b2 = testRepo.findByTest("tt")
        val c2 = testRepo.countAll()
    }*/
}