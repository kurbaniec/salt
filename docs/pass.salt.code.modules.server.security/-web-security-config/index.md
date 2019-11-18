[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [WebSecurityConfig](./index.md)

# WebSecurityConfig

`class WebSecurityConfig`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `WebSecurityConfig()` |

### Properties

| Name | Summary |
|---|---|
| [login](login.md) | `var login: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [logout](logout.md) | `var logout: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [mapping](mapping.md) | `var mapping: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [open](open.md) | `var open: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [success](success.md) | `var success: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Functions

| Name | Summary |
|---|---|
| [anyRequest](any-request.md) | `fun anyRequest(): `[`WebSecurityConfig`](./index.md) |
| [authenticated](authenticated.md) | `fun authenticated(): `[`WebSecurityConfig`](./index.md) |
| [loginPath](login-path.md) | `fun loginPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [logoutPath](logout-path.md) | `fun logoutPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [matchRequests](match-requests.md) | `fun matchRequests(vararg paths: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [permitAll](permit-all.md) | `fun permitAll(): `[`WebSecurityConfig`](./index.md) |
| [successfulLoginPath](successful-login-path.md) | `fun successfulLoginPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
