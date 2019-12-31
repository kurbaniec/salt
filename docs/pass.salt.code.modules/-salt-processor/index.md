[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [SaltProcessor](./index.md)

# SaltProcessor

`interface SaltProcessor`

Defines an interface that all Salt modules are build on.
A module can be used as a single module or class module.
A single module enables functionality through itself.
A class module enables functionality through processing classes.

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | `abstract fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = ""): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | `abstract fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [module](module.md) | [name of module](module.md#pass.salt.code.modules.SaltProcessor.Companion$module(kotlin.String, pass.salt.code.loader.config.Config, pass.salt.code.container.Container)/config)[container](module.md#pass.salt.code.modules.SaltProcessor.Companion$module(kotlin.String, pass.salt.code.loader.config.Config, pass.salt.code.container.Container)/container)`fun module(module: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`): `[`SaltProcessor`](./index.md) |
| [processClass](process-class.md) | Returns classname when a class with the specified Annotation is found, else null.`fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClass(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`?` |
| [processClassFunc](process-class-func.md) | `fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClassFunc(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>>?` |
| [processClassFuncParam](process-class-func-param.md) | `fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, F : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, P : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClassFuncParam(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>>?` |
| [processClassProp](process-class-prop.md) | `fun <C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClassProp(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>>?` |
| [processProp](process-prop.md) | `fun <A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processProp(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`KMutableProperty`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-mutable-property/index.html)`<*>>?` |

### Inheritors

| Name | Summary |
|---|---|
| [AutowiredScan](../-autowired-scan/index.md) | `class AutowiredScan : `[`SaltProcessor`](./index.md) |
| [ComponentScan](../-component-scan/index.md) | `class ComponentScan : `[`SaltProcessor`](./index.md) |
| [MappingScan](../../pass.salt.code.modules.server.mapping/-mapping-scan/index.md) | `class MappingScan : `[`SaltProcessor`](./index.md) |
| [ModuleNotFound](../-module-not-found/index.md) | `class ModuleNotFound : `[`SaltProcessor`](./index.md) |
| [MongoInit](../../pass.salt.code.modules.db.mongo/-mongo-init/index.md) | Initializes the Salt application to work with MongoDB.`class MongoInit : `[`SaltProcessor`](./index.md) |
| [MongoScan](../../pass.salt.code.modules.db.mongo/-mongo-scan/index.md) | Scans classes for [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations. If a class contains it, a concrete [MongoRepo](../../pass.salt.code.modules.db.mongo/-mongo-repo/index.md) will be initialized, that contains the custom CRUD-operations from the scanned class.`class MongoScan : `[`SaltProcessor`](./index.md) |
| [PepperServer](../../pass.salt.code.modules.server/-pepper-server/index.md) | `class PepperServer : `[`SaltProcessor`](./index.md) |
| [SaltSecurity](../../pass.salt.code.modules.server.security/-salt-security/index.md) | `class SaltSecurity : `[`SaltProcessor`](./index.md) |
| [SaltThreadPoolFactory](../-salt-thread-pool-factory/index.md) | `class SaltThreadPoolFactory : `[`SaltProcessor`](./index.md) |
| [SecurityScan](../../pass.salt.code.modules.server.security/-security-scan/index.md) | `class SecurityScan : `[`SaltProcessor`](./index.md) |
