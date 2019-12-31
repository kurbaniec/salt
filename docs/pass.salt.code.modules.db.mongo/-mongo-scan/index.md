[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoScan](./index.md)

# MongoScan

`class MongoScan : `[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md)

Scans classes for [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations.
If a class contains it, a concrete [MongoRepo](../-mongo-repo/index.md) will be initialized,
that contains the custom CRUD-operations from the scanned class.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Scans classes for [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations. If a class contains it, a concrete [MongoRepo](../-mongo-repo/index.md) will be initialized, that contains the custom CRUD-operations from the scanned class.`MongoScan(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [enabled](enabled.md) | `val enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [mongo](mongo.md) | `val mongo: `[`MongoInit`](../-mongo-init/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | `fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | `fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
