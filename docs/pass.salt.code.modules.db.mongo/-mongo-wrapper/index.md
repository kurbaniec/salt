[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoWrapper](./index.md)

# MongoWrapper

`class MongoWrapper : `[`InvocationHandler`](https://docs.oracle.com/javase/6/docs/api/java/lang/reflect/InvocationHandler.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/db/mongo/MongoWrapper.kt#L22)

Defines the logic for the proxy that will be used with [MongoRepo](../-mongo-repo/index.md).
That means all CRUD-operations will be invoked and processed through
this class.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Defines the logic for the proxy that will be used with [MongoRepo](../-mongo-repo/index.md). That means all CRUD-operations will be invoked and processed through this class.`MongoWrapper()` |

### Properties

| Name | Summary |
|---|---|
| [clazz](clazz.md) | `lateinit var clazz: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<*>` |
| [collection](collection.md) | `lateinit var collection: MongoCollection<`[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [collName](coll-name.md) | `lateinit var collName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [db](db.md) | `lateinit var db: MongoDatabase` |
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
| [type](type.md) | `lateinit var type: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [types](types.md) | `lateinit var types: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<*>, `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<*>>` |

### Functions

| Name | Summary |
|---|---|
| [invoke](invoke.md) | This method is called when an instance method is invoked on the proxy.`fun invoke(proxy: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?, method: `[`Method`](https://docs.oracle.com/javase/6/docs/api/java/lang/reflect/Method.html)`?, args: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<out `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>?): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [config](config.md) | `lateinit var config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getWrapper](get-wrapper.md) | Returns a new Proxy.`fun <W> getWrapper(cls: `[`Class`](https://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<W>, db: MongoDatabase, collName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): W` |
| [init](init.md) | `fun init(wrappy: `[`MongoWrapper`](./index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
