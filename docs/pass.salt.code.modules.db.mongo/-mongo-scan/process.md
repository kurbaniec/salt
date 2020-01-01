[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoScan](index.md) / [process](./process.md)

# process

`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/db/mongo/MongoScan.kt#L31)

Scans a class for the [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations.
If the class contains it, a concrete [MongoRepo](../-mongo-repo/index.md) will be initialized,
that contains the custom CRUD-operations from the scanned class.

