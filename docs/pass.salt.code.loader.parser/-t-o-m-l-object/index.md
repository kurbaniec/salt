[salt](../../index.md) / [pass.salt.code.loader.parser](../index.md) / [TOMLObject](./index.md)

# TOMLObject

`class TOMLObject` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/loader/parser/TOMLParser.kt#L207)

Representation of a TOML-Object.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TOMLObject(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)`<br>Representation of a TOML-Object.`TOMLObject()` |

### Properties

| Name | Summary |
|---|---|
| [attributes](attributes.md) | `var attributes: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`>` |
| [name](name.md) | `var name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [subobjects](subobjects.md) | `var subobjects: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`TOMLObject`](./index.md)`>` |

### Functions

| Name | Summary |
|---|---|
| [replaceAttribute](replace-attribute.md) | `fun replaceAttribute(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
