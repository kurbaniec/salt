[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoScan](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`MongoScan(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)`

Scans classes for [MongoDB](../../pass.salt.code.annotations/-mongo-d-b/index.md) annotations.
If a class contains it, a concrete [MongoRepo](../-mongo-repo/index.md) will be initialized,
that contains the custom CRUD-operations from the scanned class.

