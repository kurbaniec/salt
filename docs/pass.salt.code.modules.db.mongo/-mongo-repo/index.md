[salt](../../index.md) / [pass.salt.code.modules.db.mongo](../index.md) / [MongoRepo](./index.md)

# MongoRepo

`interface MongoRepo<T, I>`

### Types

| Name | Summary |
|---|---|
| [Equals](-equals/index.md) | `class Equals` |
| [Set](-set/index.md) | `class Set` |

### Functions

| Name | Summary |
|---|---|
| [countAll](count-all.md) | `abstract fun countAll(): `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) |
| [deleteAll](delete-all.md) | `abstract fun deleteAll(field: Equals): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [findAll](find-all.md) | `abstract fun findAll(): `[`ArrayList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-array-list/index.html)`<T>?` |
| [insert](insert.md) | `abstract fun insert(data: T): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [updateAll](update-all.md) | `abstract fun updateAll(fields: Equals, update: Set): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
