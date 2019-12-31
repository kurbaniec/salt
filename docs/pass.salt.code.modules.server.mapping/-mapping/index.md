[salt](../../index.md) / [pass.salt.code.modules.server.mapping](../index.md) / [Mapping](./index.md)

# Mapping

`class Mapping` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/mapping/Mapping.kt#L17)

### Types

| Name | Summary |
|---|---|
| [MappingFunction](-mapping-function/index.md) | `class MappingFunction` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Mapping(method: `[`HTTPMethod`](../-h-t-t-p-method/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [method](method.md) | `val method: `[`HTTPMethod`](../-h-t-t-p-method/index.md) |

### Functions

| Name | Summary |
|---|---|
| [addMapping](add-mapping.md) | `fun addMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, data: MappingFunction): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getMapping](get-mapping.md) | `fun getMapping(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): MappingFunction?` |
