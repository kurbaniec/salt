[salt](../../index.md) / [pass.salt.code.modules.server.webparse](../index.md) / [Webparse](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`Webparse()`

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

