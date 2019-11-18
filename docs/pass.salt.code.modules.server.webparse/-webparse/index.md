[salt](../../index.md) / [pass.salt.code.modules.server.webparse](../index.md) / [Webparse](./index.md)

# Webparse

`class Webparse`

### Types

| Name | Summary |
|---|---|
| [ParserHelp](-parser-help/index.md) | `data class ParserHelp` |
| [WebConf](-web-conf/index.md) | `data class WebConf` |
| [WebParseLoop](-web-parse-loop/index.md) | `data class WebParseLoop` |
| [WebParseText](-web-parse-text/index.md) | `data class WebParseText` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Webparse()` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `fun parse(lines: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, model: `[`Model`](../-model/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [parseLine](parse-line.md) | `fun parseLine(line: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, help: ParserHelp): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [parsePath](parse-path.md) | `fun parsePath(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, attrVal: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, model: `[`Model`](../-model/index.md)`, webConf: WebConf): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [webParse](web-parse.md) | `fun webParse(site: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, model: `[`Model`](../-model/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
