[salt](../../index.md) / [pass.salt.code.loader.config](../index.md) / [Config](./index.md)

# Config

`class Config` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/loader/config/Config.kt#L14)

Handles the configuration of the Salt framework.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Handles the configuration of the Salt framework.`Config()` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`TOMLParser`](../../pass.salt.code.loader.parser/-t-o-m-l-parser/index.md) |
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html) |

### Functions

| Name | Summary |
|---|---|
| [findAttribute](find-attribute.md) | Returns the value of an attribute found in the configuration.`fun <T> findAttribute(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): T` |
| [findObjectAttribute](find-object-attribute.md) | Returns the value of an object attribute found in the configuration.`fun <T> findObjectAttribute(obj: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, attr: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): T` |
| [setLoggerLevel](set-logger-level.md) | Set the logging level of the Salt application.`fun setLoggerLevel(level: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
