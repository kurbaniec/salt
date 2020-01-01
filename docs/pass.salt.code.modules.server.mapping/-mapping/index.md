[salt](../../index.md) / [pass.salt.code.modules.server.mapping](../index.md) / [Mapping](./index.md)

# Mapping

`class Mapping` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/mapping/Mapping.kt#L20)

Stores all request mappings for a given [HTTPMethod](../-h-t-t-p-method/index.md).

### Types

| Name | Summary |
|---|---|
| [MappingFunction](-mapping-function/index.md) | Wraps a function so that it can be easily called.`class MappingFunction` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Stores all request mappings for a given [HTTPMethod](../-h-t-t-p-method/index.md).`Mapping(method: `[`HTTPMethod`](../-h-t-t-p-method/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [method](method.md) | `val method: `[`HTTPMethod`](../-h-t-t-p-method/index.md) |

### Functions

| Name | Summary |
|---|---|
| [addMapping](add-mapping.md) | Adds wrapped function for a specific request path.`fun addMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: MappingFunction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getMapping](get-mapping.md) | Returns wrapped function for a specific request path.`fun getMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): MappingFunction?` |
