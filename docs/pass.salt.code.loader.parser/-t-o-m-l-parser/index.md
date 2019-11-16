[salt](../../index.md) / [pass.salt.code.loader.parser](../index.md) / [TOMLParser](./index.md)

# TOMLParser

`class TOMLParser`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TOMLParser(fileName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`)` |

### Functions

| Name | Summary |
|---|---|
| [findAttribute](find-attribute.md) | `fun findAttribute(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [findObject](find-object.md) | `fun findObject(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`TOMLObject`](../-t-o-m-l-object/index.md)`?` |
| [findObjectAttribute](find-object-attribute.md) | `fun findObjectAttribute(obj: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, attr: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
| [overdrive](overdrive.md) | Overwrite this config with the [other](overdrive.md#pass.salt.code.loader.parser.TOMLParser$overdrive(pass.salt.code.loader.parser.TOMLParser)/other) one. TODO test overdrive - highly experimental`fun overdrive(other: `[`TOMLParser`](./index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [replaceAttribute](replace-attribute.md) | Replaces attribute value or creates new attribute with the given [value](replace-attribute.md#pass.salt.code.loader.parser.TOMLParser$replaceAttribute(kotlin.String, kotlin.Any)/value).`fun replaceAttribute(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [replaceObjectAttribute](replace-object-attribute.md) | Replaces the value of an attribute [name](replace-object-attribute.md#pass.salt.code.loader.parser.TOMLParser$replaceObjectAttribute(kotlin.String, kotlin.String, kotlin.Any)/name) of an object [obj](replace-object-attribute.md#pass.salt.code.loader.parser.TOMLParser$replaceObjectAttribute(kotlin.String, kotlin.String, kotlin.Any)/obj) with the given [value](replace-object-attribute.md#pass.salt.code.loader.parser.TOMLParser$replaceObjectAttribute(kotlin.String, kotlin.String, kotlin.Any)/value). Does nothing when the object is not found.`fun replaceObjectAttribute(obj: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, value: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [subOverdrive](sub-overdrive.md) | `fun subOverdrive(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, subobj: `[`HashMap`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-hash-map/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, `[`TOMLObject`](../-t-o-m-l-object/index.md)`>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
