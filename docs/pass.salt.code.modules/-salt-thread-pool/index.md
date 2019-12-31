[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [SaltThreadPool](./index.md)

# SaltThreadPool

`class SaltThreadPool` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/SaltThreadPool.kt#L43)

Representation of an executor service that initializes a concrete one from a given type.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Representation of an executor service that initializes a concrete one from a given type.`SaltThreadPool(type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, threadCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [shutdown](shutdown.md) | Shutdown executor service.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [submit](submit.md) | Submit new task to executor.`fun submit(task: `[`Runnable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Runnable.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
