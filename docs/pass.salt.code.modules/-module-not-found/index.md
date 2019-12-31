[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [ModuleNotFound](./index.md)

# ModuleNotFound

`class ModuleNotFound : `[`SaltProcessor`](../-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/ModuleNotFound.kt#L8)

Dummy class that is created when an unknown module is requested.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Dummy class that is created when an unknown module is requested.`ModuleNotFound()` |

### Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Log warning that given module is not known.`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Not used.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
