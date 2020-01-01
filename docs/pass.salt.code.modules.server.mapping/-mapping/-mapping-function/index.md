[salt](../../../index.md) / [pass.salt.code.modules.server.mapping](../../index.md) / [Mapping](../index.md) / [MappingFunction](./index.md)

# MappingFunction

`class MappingFunction` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/mapping/Mapping.kt#L40)

Wraps a function so that it can be easily called.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Wraps a function so that it can be easily called.`MappingFunction(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, instance: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, func: `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>)` |

### Properties

| Name | Summary |
|---|---|
| [func](func.md) | `val func: `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>` |
| [hasModel](has-model.md) | `var hasModel: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasSessionUser](has-session-user.md) | `var hasSessionUser: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [instance](instance.md) | `val instance: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [model](model.md) | `var model: `[`Model`](../../../pass.salt.code.modules.server.webparse/-model/index.md)`?` |
| [params](params.md) | `val params: `[`LinkedHashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-linked-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [path](path.md) | `val path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [sessionUser](session-user.md) | `var sessionUser: `[`SessionUser`](../../../pass.salt.code.modules.server.security/-session-user/index.md)`?` |
| [sessionUserKey](session-user-key.md) | `var sessionUserKey: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addParams](add-params.md) | Adds parameter to wrapper that will be used in function call.`fun addParams(params: `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [call](call.md) | Invokes wrapped function.`fun call(): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [hasModel](has-model.md) | `fun hasModel(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
