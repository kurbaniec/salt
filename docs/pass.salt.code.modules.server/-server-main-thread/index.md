[salt](../../index.md) / [pass.salt.code.modules.server](../index.md) / [ServerMainThread](./index.md)

# ServerMainThread

`class ServerMainThread<P : `[`ServerSocket`](https://docs.oracle.com/javase/6/docs/api/java/net/ServerSocket.html)`> : `[`Runnable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Runnable.html)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ServerMainThread(executor: `[`SaltThreadPool`](../../pass.salt.code.modules/-salt-thread-pool/index.md)`, serverSocket: P, mapping: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Mapping`](../../pass.salt.code.modules.server.mapping/-mapping/index.md)`>, config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, security: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, `[`SaltSecurity`](../../pass.salt.code.modules.server.security/-salt-security/index.md)`?>)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [executor](executor.md) | `val executor: `[`SaltThreadPool`](../../pass.salt.code.modules/-salt-thread-pool/index.md) |
| [listening](listening.md) | `var listening: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [mapping](mapping.md) | `val mapping: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Mapping`](../../pass.salt.code.modules.server.mapping/-mapping/index.md)`>` |
| [security](security.md) | `val security: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, `[`SaltSecurity`](../../pass.salt.code.modules.server.security/-salt-security/index.md)`?>` |
| [serverSocket](server-socket.md) | `val serverSocket: P` |

### Functions

| Name | Summary |
|---|---|
| [addGetMapping](add-get-mapping.md) | `fun addGetMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, call: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [addPostMapping](add-post-mapping.md) | `fun addPostMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, call: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getGetMapping](get-get-mapping.md) | `fun getGetMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): MappingFunction?` |
| [getMapping](get-mapping.md) | `fun getMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, method: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): MappingFunction?` |
| [getPostMapping](get-post-mapping.md) | `fun getPostMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): MappingFunction?` |
| [run](run.md) | init { val getMapping = mutableMapOf&lt;String, Pair&lt;Any, KFunction&lt;*&gt;&gt;&gt;()val postMapping = mutableMapOf&lt;String, Pair&lt;Any, KFunction&lt;*&gt;&gt;&gt;() mapping[get](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/get.html) = getMapping mapping[post](#) = postMapping }`fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
