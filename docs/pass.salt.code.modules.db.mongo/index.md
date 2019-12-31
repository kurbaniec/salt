[salt](../index.md) / [pass.salt.code.modules.db.mongo](./index.md)

## Package pass.salt.code.modules.db.mongo

MongoDB module of Salt.

### Types

| Name | Summary |
|---|---|
| [MongoInit](-mongo-init/index.md) | Initializes the Salt application to work with MongoDB.`class MongoInit : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |
| [MongoRepo](-mongo-repo/index.md) | Defines basic CRUD-operations to interact with MongoDB.`interface MongoRepo<T, I>` |
| [MongoScan](-mongo-scan/index.md) | Scans classes for [MongoDB](../pass.salt.code.annotations/-mongo-d-b/index.md) annotations. If a class contains it, a concrete [MongoRepo](-mongo-repo/index.md) will be initialized, that contains the custom CRUD-operations from the scanned class.`class MongoScan : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |
| [MongoWrapper](-mongo-wrapper/index.md) | Defines the logic for the proxy that will be used with [MongoRepo](-mongo-repo/index.md). That means all CRUD-operations will be invoked and processed through this class.`class MongoWrapper : `[`InvocationHandler`](https://docs.oracle.com/javase/6/docs/api/java/lang/reflect/InvocationHandler.html) |
