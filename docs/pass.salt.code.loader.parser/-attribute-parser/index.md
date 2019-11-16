[salt](../../index.md) / [pass.salt.code.loader.parser](../index.md) / [AttributeParser](./index.md)

# AttributeParser

`interface AttributeParser`

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `abstract fun parse(attribute: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(attribute: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`AttributeParser`](./index.md) |

### Inheritors

| Name | Summary |
|---|---|
| [ArrayParser](../-array-parser/index.md) | `class ArrayParser : `[`AttributeParser`](./index.md) |
| [BooleanParser](../-boolean-parser/index.md) | `class BooleanParser : `[`AttributeParser`](./index.md) |
| [FloatParser](../-float-parser/index.md) | `class FloatParser : `[`AttributeParser`](./index.md) |
| [IntegerParser](../-integer-parser/index.md) | `class IntegerParser : `[`AttributeParser`](./index.md) |
| [StringParser](../-string-parser/index.md) | `class StringParser : `[`AttributeParser`](./index.md) |
