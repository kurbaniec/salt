[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [SaltProcessor](./index.md)

# SaltProcessor

`interface SaltProcessor` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/SaltProcessor.kt#L26)

Defines an interface that all Salt modules are build on.
A module can be used as a single module or class module.
A single module enables functionality through itself.
A class module enables functionality through processing classes.

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Process module.`abstract fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Shutdown module.`abstract fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [module](module.md) | Simple module factory. All modules are created and returned through this method.`fun module(module: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`): `[`SaltProcessor`](./index.md) |
| [processClass](process-class.md) | Returns classname when a class with the specified annotation is found, else null.`fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClass(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [processClassFunc](process-class-func.md) | Searches for specific functions annotated with annotation A in a class that is annotated with annotation C. Found functions will be returned as Pairs with their annotation`fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClassFunc(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>>?` |
| [processProp](process-prop.md) | Returns properties of a class that are annotated with the given annotation.`fun <A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processProp(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`KMutableProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-mutable-property/index.html)`<*>>?` |

### Inheritors

| Name | Summary |
|---|---|
| [AutowiredScan](../-autowired-scan/index.md) | Scans classes and injects values to the class properties from the [Container](../../pass.salt.code.container/-container/index.md) if the property contains the [Autowired](../../pass.salt.code.annotations/-autowired/index.md) annotation.`class AutowiredScan : `[`SaltProcessor`](./index.md) |
| [ComponentScan](../-component-scan/index.md) | Scans classes and adds them to the [Container](../../pass.salt.code.container/-container/index.md) if they contain the [Scan](../../pass.salt.code.annotations/-scan/index.md) annotation.`class ComponentScan : `[`SaltProcessor`](./index.md) |
| [MappingScan](../../pass.salt.code.modules.server.mapping/-mapping-scan/index.md) | Scans classes marked with [Controller](../../pass.salt.code.annotations/-controller/index.md) to look after [Get](../../pass.salt.code.annotations/-get/index.md) and [Post](../../pass.salt.code.annotations/-post/index.md) mappings.`class MappingScan : `[`SaltProcessor`](./index.md) |
| [ModuleNotFound](../-module-not-found/index.md) | Dummy class that is created when an unknown module is requested.`class ModuleNotFound : `[`SaltProcessor`](./index.md) |
| [MongoInit](../../pass.salt.code.modules.db.mongo/-mongo-init/index.md) | Initializes the Salt application to work with MongoDB.`class MongoInit : `[`SaltProcessor`](./index.md) |
| [MongoScan](../../pass.salt.code.modules.db.mongo/-mongo-scan/index.md) | Scans classes for [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations. If a class contains it, a concrete [MongoRepo](../../pass.salt.code.modules.db.mongo/-mongo-repo/index.md) will be initialized, that contains the custom CRUD-operations from the scanned class.`class MongoScan : `[`SaltProcessor`](./index.md) |
| [PepperServer](../../pass.salt.code.modules.server/-pepper-server/index.md) | Represents the Pepper webserver that is used by the Salt framework. Pepper consist of two [ServerMainThread](../../pass.salt.code.modules.server/-server-main-thread/index.md)s that serve http and https.`class PepperServer : `[`SaltProcessor`](./index.md) |
| [SaltSecurity](../../pass.salt.code.modules.server.security/-salt-security/index.md) | Handles all security functionality of Salt. This includes authentication and authentication of requests.`class SaltSecurity : `[`SaltProcessor`](./index.md) |
| [SaltThreadPoolFactory](../-salt-thread-pool-factory/index.md) | Initializes executor service and thread pool for Salt.`class SaltThreadPoolFactory : `[`SaltProcessor`](./index.md) |
| [SecurityScan](../../pass.salt.code.modules.server.security/-security-scan/index.md) | Scans classes for security configurations. This classes are annotated with the [WebSecurity](../../pass.salt.code.annotations/-web-security/index.md) annotation and implement the [WebSecurityConfigurator](../../pass.salt.code.modules.server.security/-web-security-configurator/index.md) interface.`class SecurityScan : `[`SaltProcessor`](./index.md) |
