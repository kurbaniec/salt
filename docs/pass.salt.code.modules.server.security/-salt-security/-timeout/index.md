[salt](../../../index.md) / [pass.salt.code.modules.server.security](../../index.md) / [SaltSecurity](../index.md) / [Timeout](./index.md)

# Timeout

`class Timeout` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/SaltSecurity.kt#L168)

Handles timeout of clients that cannot use the application service because of too many failed authentication
requests.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Handles timeout of clients that cannot use the application service because of too many failed authentication requests.`Timeout(timeout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, fails: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [fails](fails.md) | `val fails: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [mapping](mapping.md) | `val mapping: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<LocalDateTime>` |
| [stopInit](stop-init.md) | `var stopInit: LocalDateTime?` |
| [timeout](timeout.md) | `val timeout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addStrike](add-strike.md) | Add failed authentication event.`fun addStrike(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [clear](clear.md) | Clear timeout.`fun clear(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [process](process.md) | Check if timeout for a client persists or is still in place.`fun process(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
