[salt](../../index.md) / [pass.salt.code.loader](../index.md) / [Loader](./index.md)

# Loader

`class Loader` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/loader/Loader.kt#L17)

Loads the Salt application.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Loads the Salt application.`Loader()` |

### Properties

| Name | Summary |
|---|---|
| [classModules](class-modules.md) | `val classModules: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md)`>` |
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
| [singleModules](single-modules.md) | `val singleModules: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [shutdown](shutdown.md) | `fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
