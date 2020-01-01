[salt](../../index.md) / [pass.salt.code.modules.server.encryption](../index.md) / [SSLManager](./index.md)

# SSLManager

`class SSLManager` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/encryption/SSLManager.kt#L18)

Contains all SSL-Configuration for the HTTPS-[ServerMainThread](../../pass.salt.code.modules.server/-server-main-thread/index.md).

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Contains all SSL-Configuration for the HTTPS-[ServerMainThread](../../pass.salt.code.modules.server/-server-main-thread/index.md).`SSLManager()` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [createKeyStore](create-key-store.md) | Create keystore.`fun createKeyStore(password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [createSSLContext](create-s-s-l-context.md) | Create the and initialize the SSLContext`fun createSSLContext(password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, file: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`SSLContext`](https://docs.oracle.com/javase/6/docs/api/javax/net/ssl/SSLContext.html)`?` |
