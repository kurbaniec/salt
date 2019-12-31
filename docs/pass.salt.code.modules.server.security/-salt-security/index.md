[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [SaltSecurity](./index.md)

# SaltSecurity

`class SaltSecurity : `[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/SaltSecurity.kt#L12)

### Types

| Name | Summary |
|---|---|
| [Timeout](-timeout/index.md) | `class Timeout` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SaltSecurity(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [auth](auth.md) | `lateinit var auth: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>` |
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [login](login.md) | `var login: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [logout](logout.md) | `var logout: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [mapping](mapping.md) | `val mapping: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [open](open.md) | `var open: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [passwdFails](passwd-fails.md) | `var passwdFails: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [passwdTimeout](passwd-timeout.md) | `var passwdTimeout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [sessions](sessions.md) | `val sessions: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`SessionUser`](../-session-user/index.md)`>` |
| [sessionTimeout](session-timeout.md) | `val sessionTimeout: `[`MutableMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, Timeout>` |
| [success](success.md) | `var success: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [timeout](timeout.md) | `var timeout: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [addSession](add-session.md) | `fun addSession(username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [checkAuthorization](check-authorization.md) | `fun checkAuthorization(basicAuth: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [getSession](get-session.md) | `fun getSession(sid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`SessionUser`](../-session-user/index.md)`?` |
| [isValidSession](is-valid-session.md) | `fun isValidSession(sid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [process](process.md) | `fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeSession](remove-session.md) | `fun removeSession(sid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [setConfiguration](set-configuration.md) | `fun setConfiguration(conf: `[`WebSecurityConfig`](../-web-security-config/index.md)`, auth: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | `fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
