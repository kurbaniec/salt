[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [SessionUser](./index.md)

# SessionUser

`class SessionUser` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/SessionUser.kt#L8)

Contains the client information for the session at runtime.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SessionUser(username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, sid: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, sec: `[`SaltSecurity`](../-salt-security/index.md)`)`<br>Contains the client information for the session at runtime.`SessionUser()` |

### Properties

| Name | Summary |
|---|---|
| [init](init.md) | `var init: LocalDateTime!` |
| [username](username.md) | `lateinit var username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [endSession](end-session.md) | End client session.`fun endSession(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
