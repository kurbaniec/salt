[salt](../../index.md) / [pass.salt.code.modules.server.webparse](../index.md) / [WebTools](./index.md)

# WebTools

`class WebTools` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/webparse/WebTools.kt#L8)

Utility functions for advanced HTML-template functionality.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Utility functions for advanced HTML-template functionality.`WebTools()` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [lo](lo.md) | `val lo: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [lo2](lo2.md) | `val lo2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [lo3](lo3.md) | `val lo3: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [script](script.md) | `val script: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [script2](script2.md) | `val script2: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [script3](script3.md) | `val script3: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [script4](script4.md) | `val script4: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [buildLogin](build-login.md) | Returns js login script when `<th:login/>` is found in a HTML-site.`fun buildLogin(preUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, login: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, success: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [buildLogout](build-logout.md) | Returns js logout script when `<th:logout/>` is found in a HTML-site.`fun buildLogout(preUrl: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, logout: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, login: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getIPAddress](get-i-p-address.md) | Returns the ip-address of the Salt application.`fun getIPAddress(conf: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
