[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [WebSecurityConfig](./index.md)

# WebSecurityConfig

`class WebSecurityConfig` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/WebSecurityConfig.kt#L51)

Configures authentication on request paths. Also configures login/logout and success path.

If [permitAll](permit-all.md) is called on a [matchRequests](match-requests.md), the configuration is open, only mapped entries are secured.

If [authenticated](authenticated.md) is called on a [matchRequests](match-requests.md), the configuration is not open, mapped entries will not secured
but every other request.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Configures authentication on request paths. Also configures login/logout and success path.`WebSecurityConfig()` |

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
| [anyRequest](any-request.md) | Used to select any request path beside mapped ones.`fun anyRequest(): `[`WebSecurityConfig`](./index.md) |
| [authenticated](authenticated.md) | Permit only authenticated access on selected request paths.`fun authenticated(): `[`WebSecurityConfig`](./index.md) |
| [loginPath](login-path.md) | Configure login request path.`fun loginPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [logoutPath](logout-path.md) | Configure logout request path.`fun logoutPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [matchRequests](match-requests.md) | Used to select multiple request path manually.`fun matchRequests(vararg paths: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
| [permitAll](permit-all.md) | Permit access on selected request paths.`fun permitAll(): `[`WebSecurityConfig`](./index.md) |
| [successfulLoginPath](successful-login-path.md) | Configure request path the client will be redirected when successfully loggend in.`fun successfulLoginPath(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`WebSecurityConfig`](./index.md) |
