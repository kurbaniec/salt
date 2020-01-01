<h1 align="center">
  <br>
  <img src="init/resources/web/favicon.ico" alt="solar-system" width="250"></a>
  <br>
  <br>
   Salt
   <h3>
       Salt is a simple application framework that features an inversion of control container for usage with Kotlin.
   </h3>
</h1>

# 🛠️Setup

1. Create new Gradle project with Kotlin/JVM as main language

2. Insert the Salt framework with `` git submodule add https://github.com/kurbaniec-tgm/salt.git salt`` under `src/main/kotlin/[main-package]`

   > Note: Do not work directly in the `salt` package! Create a second package for your code.

3. Use `gradle initSalt` under `src/main/kotlin/[main-package]/salt` to initialize the framework

4. Add the following dependencies to your `build.gradle`:

   ```
   implementation platform('org.jetbrains.kotlin:kotlin-bom')
   implementation 'org.jetbrains.kotlin:kotlin-stdlib-jdk8'
   compile 'org.jetbrains.kotlin:kotlin-test'
   compile 'org.jetbrains.kotlin:kotlin-test-junit'
   compile group: 'org.jetbrains.kotlin', name: 'kotlin-reflect', version: '1.3.60'
   compile group: 'org.mongodb', name: 'mongo-java-driver', version: '3.9.1'
   compile 'net.sourceforge.htmlunit:htmlunit:2.36.0'
   ```

5. Lastly, define a `main`-method that starts the Salt application:

   ```kotlin
   class Main {
       companion object {
           @JvmStatic
           fun main(args: Array<String>) {
               val app = SaltApplication()
           }
       }
   }
   ```

# 🧂 Working with Salt

<!--

## Project Structure TODO Update
Following project structure is needed.
```bash    
.    
├── out 
|   └── production   
|       └── project_name   
|           └── packages_and_class_files   
├── res   
|   ├── default.toml   
|   ├── config.toml   
|   └── web    
|       └── html_files   
├── src 
|   └── main_package_!important
|       └── other_packages_and_classes 
```
-->

## Dependency Injection

Just mark a class with `@Scan` and use `@Autowired` on a property with the same name/type in another class to automatically inject an instance of the scanned class to the property. Note, the class in which the object is injected also needs to be marked with `@Scan` or a annotation that includes it like `@Controller`.

```kotlin
@Scan
class AutowiredTest {
    val value = "Test"
}
```

```kotlin
@Scan
class SomeClass {
    @Autowired
    lateinit var autowiredTest: AutowiredTest     
}
```

## Serve web content

Salt can serve any file you want, just drop it into the `resources/web` directory.

To configure request mappings for HTML-files create a new Controller class (annotated with `@Controller`and define mapping functions.

A mapping functions consists of a method type annotation (`@Get` or `@Post` ) that contains the request path. The function itself returns a String, that is the filename of the page that should be returned without `.html`.

Following code shows how to return a HTML page on the request path `/` based on the file `index.html`-

```kotlin
@Controller
class Controller {
    
    @Get("/")
    fun index(): String {
        return "index"
    }
}
```

At the moment the mapping functions can also return simple HTTP-responses for simple data transfer through the HTTP-body.

Following code shows how to transport two parameters ("Hi" and "Salt") to an client, when it sends a post request at `/api`.

```kotlin
@Controller
class Controller {
    
    @Post("/api")
    fun api(): HTTPTransport {
        return HTTPTransport(HTTPTransport.Body("Hi", "Salt")).ok()
    }
}
```

## HTML-Template Engine

The custom template parser is inspired by Thymeleaf and supports for now following functionality:  

* ```js
  <span th:text="Hello, ${message}"></span>
  ```

  The `th:text` attribute will replace the value between tags eg. `span`. `${message}` stands for an object that
  is added in the controller via `model.addAttribute("message", "baum")`. So the ouput, when parsed will be `<span>Hello, baum</span>`.
  If baum is for example not a String but an other object like `User` added as `user` with a attribute `login`, you can also acces it  via `${user.login}`.

* ```js
  <a th:href="@{/some/path/${testo}}">Link!</a>
  ```

  Creates a link that can refer to other pages or resources of the application. As seen, you can add also models like `${testo}` that  will be resolved to create a dynamic link.

* ```js
  <th:block th:each="user : ${users}">
      <span th:text="user ${user.id}"></span>
  </th:block>
  ```

  Creates a loop of a block marked with `th:block`. `users` is a list added via a model in the controller. For every entry of `users` marked as `user` the block will be dynamically created.     
  Note: No `<th:block>` will be seen on the parsed site.

To enable the template engine, the html-page needs to be mapped in an controller an contain a `Model` object in the function call. All the information that you want to parse need to be added to the `Model` object.

You have a page that contains following template code:

```js
<span th:text="Hello, ${message}"></span>

<th:block th:each="user : ${users}">
    <span th:text="user ${user.id}"></span>
</th:block>
```

Then you can use following code to return the page at `/template` with injected values.

```kotlin
@Controller
class Controller {
    
    @Get("/template")
    fun template(m: Model): String {
        data class User(val id: Int)
        m.addAttribute("message", "Salt")
        m.addAttribute("users", listOf(User(0), User(1)))
        return "template"
    }
}
```

The following result should be seen when visiting `/template` now.

![](images/templateTest.PNG)

## MongoDB

Salt makes performing CRUD operations on a remote Mongo database very easy.

To enable the MongoDB plugin, add the following configuration to `resources\config.toml`

```toml
[mongo]
enable = true
uri = "mongodb://localhost:27017"
db = "dev"
UserRepo = "users"
```

This way Salt connects on startup to the remote database and binds the configuration interface `UserRepo` to the `users` collection in the `dev` database of MongoDB.

Now the `UserRepo` interface needs to be created and marked with the `@MondoDB` annotation. It also needs to implement the `MongoRepo` witch the type that we want to serialize to the database and String as second argument. With the implementation we gain a lot of basic functions like `ìnsert` or `findAll`.

But the true magic is, that you can easily define your own queries. Lets say the class `User` has a property `username`. Then you can create an abstract method `fun findByUsername(username: String): List<User>?` that generates the query for you and that you can call at runtime.  You can also look after more parameters in a query to the inclusion of `And`.

Following code show some possible queries.

```kotlin
@MongoDB
interface UserRepo: MongoRepo<User, String> {

    fun findByUsername(username: String): List<User>?

    fun findByUsernameAndPassword(username: String, password: String): List<User>?

    fun findByMyidAndUsername(myid: String, username: String): List<User>?

    fun findByMyidAndUsernameAndPassword(myid: String, username: String, password: String): List<User>?

    fun findByMyid(myid: String): List<User>?

}
```

To use the `MongoRepo` in your code to perform CRUD-operations, just use the Salt dependency injection.

In this case that would look like that:

```kotlin
@Autowired
lateinit var userRepo: UserRepo
```

## Configure Salt

Some aspects of the application can be configured in the configuration file found under`resources\config.toml`. Here are some of the most important ones:

**[Server]:**

* `ip_address`: The ip-address the server is bound to.
* `https_port`: The port the server listens to https-requests.
  * Note: There is also a `http_port` setting, but you should not use the http-protocol with sensitive data in use.    
    You can disable it with `http = false` .

**[Security]**:

* `timeout`: The time, in which the user is automatically logged out when no request is send in the meantime. In minutes.
* `password_timeout`:  The timeout, in which a user can not log in, because he used a wrong password to often. In minutes.
* `password_fails`: Sets the amount of bad authentication needed to trigger the login timeout.

**[Keystore]**:

* `generate`: Use `true` if you want to automatically create a certificate for the https-encryption. Be aware that this certificate is not signed by any authority and therefore not trusted by most browsers. If you have a valid certificate use `false` and fill the `file`-attribute.
* `file`: If you have a TLS-certificate for your domain, convert it into a java-keystore (`.jks`-file) and put it into the `res` folder.  The `file`-attribute should be the name of this file.
* `password`: The password the generated certificate should use or the password to your own keystore.

**[Mongo]:**

* `uri`: The connection description to your MongoDB-database in the "String URI Format". Click [here][5] for more information.
* `UserRepo`: Name of your collection that will store user-accounts.
* `PasswordRepo`: Name of your collection that will store password-entity data.

## Naming conventions
Classes can for now not end with `Kt` or `$1` (because kotlin creates `[className](Kt|$1).class` files).

<!--

## Security concept

* Use https for tls encryption
* Login via Authorization header
* With successfull login -> send back valid session cookie
* Logout or timeout -> delete session cookie

## TOML-Parser (for config-files)

Supports:

* Boolean, Float, Integer, String and Array-Datatypes
* Arrays can be nested
* Multiline-Array initialization is not supported
* Sections and sub-sections are supported
* Full-Line and End-Line comments are supported

## MongoDB

MongoDB is used as a database, therefore the mongo-java-driver is needed. You can find him [here](http://central.maven.org/maven2/org/mongodb/mongo-java-driver/).

# TODO

- [ ] Write Test Cases
- [ ] PasswordManager: Support letsencrypt for https keystore
- [ ] PasswordManager: Don´t load all passwords at once, instead send them on a request basis
- [ ] Terminator-service
- [ ] ssl certificate renew -> swap socket?
- [X] PasswordManager: Account page / update password (with old one as test) and delete user
- [X] PasswordManager: copy to clipboard / update links / delete entries
- [X] Logout functionality
- [X] Database-mongo wrapper for salt
- [X] convert login in script to th:login
- [X] Config default page when logged on
- [x] Thymeleaf Parser?
- [x] Autherization header + session + cookie
- [x] cookie session key
- [x] redirect http->https



-->

## License

MIT

---

> GitHub [kurbaniec](https://github.com/kurbaniec-tgm) &nbsp;&middot;&nbsp;
> Mail [at.kacper.urbaniec@gmail.com](mailto:at.kacper.urbaniec@gmail.com)
