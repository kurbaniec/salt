[salt](../../index.md) / [pass.salt.code.modules.server.mapping](../index.md) / [MappingScan](./index.md)

# MappingScan

`class MappingScan : `[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/mapping/MappingScan.kt#L15)

Scans classes marked with [Controller](../../pass.salt.code.annotations/-controller/index.md) to look after [Get](../../pass.salt.code.annotations/-get/index.md) and [Post](../../pass.salt.code.annotations/-post/index.md) mappings.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Scans classes marked with [Controller](../../pass.salt.code.annotations/-controller/index.md) to look after [Get](../../pass.salt.code.annotations/-get/index.md) and [Post](../../pass.salt.code.annotations/-post/index.md) mappings.`MappingScan(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Scans a class and looks if its marked with [Controller](../../pass.salt.code.annotations/-controller/index.md). If yes, then it looks afters [Get](../../pass.salt.code.annotations/-get/index.md) and [Post](../../pass.salt.code.annotations/-post/index.md) mappings that are added to the [PepperServer](../../pass.salt.code.modules.server/-pepper-server/index.md).`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Not used.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
