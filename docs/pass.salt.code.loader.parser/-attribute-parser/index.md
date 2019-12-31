[salt](../../index.md) / [pass.salt.code.loader.parser](../index.md) / [AttributeParser](./index.md)

# AttributeParser

`interface AttributeParser` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/loader/parser/TOMLAttributeParser.kt#L7)

Interfaces that defines parsing behaviour.
Also, includes a method for correct parsing of an given String.

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | Parse String value to correct type of given value.`abstract fun parse(attribute: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |

### Companion Object Functions

| Name | Summary |
|---|---|
| [check](check.md) | `fun check(attribute: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`AttributeParser`](./index.md) |

### Inheritors

| Name | Summary |
|---|---|
| [ArrayParser](../-array-parser/index.md) | Used for parsing Arrays.`class ArrayParser : `[`AttributeParser`](./index.md) |
| [BooleanParser](../-boolean-parser/index.md) | Used for parsing booleans.`class BooleanParser : `[`AttributeParser`](./index.md) |
| [FloatParser](../-float-parser/index.md) | Used for parsing Floats.`class FloatParser : `[`AttributeParser`](./index.md) |
| [IntegerParser](../-integer-parser/index.md) | Used for parsing Integers.`class IntegerParser : `[`AttributeParser`](./index.md) |
| [StringParser](../-string-parser/index.md) | Used for parsing String.`class StringParser : `[`AttributeParser`](./index.md) |
