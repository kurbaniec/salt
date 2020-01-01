[salt](../../index.md) / [pass.salt.code.modules.server.webparse](../index.md) / [Webparse](./index.md)

# Webparse

`class Webparse` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/webparse/Webparse.kt#L37)

Handles parsing of HTML-sites using the Salt template syntax.

Template parser functionality:

``` js
<span th:text="Hello, ${message}"></span>
```

The `th:text` attribute will replace the value between tags eg. `span`. `${message}` stands for an object that
is added in the controller via `model.addAttribute("message", "baum")`. So the ouput, when parsed will be `<span>Hello, baum</span>`.
If baum is for example not a String but an other object like `User` added as `user` with a attribute `login`, you can also acces it     via `${user.login}`.

``` js
<a th:href="@{/some/path/${testo}}">Link!</a>
```

Creates a link that can refer to other pages or resources of the application. As seen, you can add also models like `${testo}` that     will be resolved to create a dynamic link.

``` js
<th:block th:each="user : ${users}">
  <tr style="border: 1px solid black">
    <td style="border: 1px solid black" th:text="${user.login}">...</td>
    <td th:text="${user.name}">...</td>
  </tr>
  <tr>
    <td th:text="${user.address}">...</td>
  </tr>
</th:block>
```

Creates a loop of a block marked with `th:block`. `users` is a list added via a model in the controller. For every entry of `users`     marked as `user` the block will be dynamically created. Note: No `<th:block>` will be seen on the parsed site.

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
| [&lt;init&gt;](-init-.md) | Handles parsing of HTML-sites using the Salt template syntax.`Webparse()` |

### Companion Object Properties

| Name | Summary |
|---|---|
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | Parses a file that is given as a list of lines. Also a [Model](../-model/index.md) is given to inject values to the template.`fun parse(lines: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, model: `[`Model`](../-model/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [parseLine](parse-line.md) | Parses a line and returns a list of HTML elements.`fun parseLine(line: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, help: ParserHelp): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>` |
| [parsePath](parse-path.md) | Webparse helper method.`fun parsePath(tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, attrVal: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, model: `[`Model`](../-model/index.md)`, webConf: WebConf): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [webParse](web-parse.md) | Parses the file truly through the list of HTML-elements.`fun webParse(site: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, model: `[`Model`](../-model/index.md)`): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
