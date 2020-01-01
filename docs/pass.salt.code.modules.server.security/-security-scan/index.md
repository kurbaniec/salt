[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [SecurityScan](./index.md)

# SecurityScan

`class SecurityScan : `[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/SecurityScan.kt#L13)

Scans classes for security configurations. This classes are annotated with the [WebSecurity](../../pass.salt.code.annotations/-web-security/index.md) annotation and
implement the [WebSecurityConfigurator](../-web-security-configurator/index.md) interface.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Scans classes for security configurations. This classes are annotated with the [WebSecurity](../../pass.salt.code.annotations/-web-security/index.md) annotation and implement the [WebSecurityConfigurator](../-web-security-configurator/index.md) interface.`SecurityScan(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [configured](configured.md) | `var configured: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Scans class if it contains security configurations. If yes, then the security configuration will be enabled.`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Not used.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
