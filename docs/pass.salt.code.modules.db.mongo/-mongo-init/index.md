[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoInit](./index.md)

# MongoInit

`class MongoInit : `[`SaltProcessor`](../../pass.salt.code.modules/-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/db/mongo/MongoInit.kt#L25)

Initializes the Salt application to work with MongoDB.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Initializes the Salt application to work with MongoDB.`MongoInit(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [collection](collection.md) | `lateinit var collection: MongoCollection<Document>` |
| [collName](coll-name.md) | `lateinit var collName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |
| [db](db.md) | `lateinit var db: MongoDatabase` |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
| [mongoClient](mongo-client.md) | `lateinit var mongoClient: MongoClient` |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | `fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | `fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
