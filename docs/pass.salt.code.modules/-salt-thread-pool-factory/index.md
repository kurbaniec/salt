[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [SaltThreadPoolFactory](./index.md)

# SaltThreadPoolFactory

`class SaltThreadPoolFactory : `[`SaltProcessor`](../-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/SaltThreadPool.kt#L13)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SaltThreadPoolFactory(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [executor](executor.md) | `lateinit var executor: `[`SaltThreadPool`](../-salt-thread-pool/index.md) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | `fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | `fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
