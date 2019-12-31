[salt](../../index.md) / [pass.salt.code.container](../index.md) / [Container](./index.md)

# Container

`class Container` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/container/Container.kt#L9)

Handles all values managed by the Salt Framework.
Enables "Inversion of Control" and therefore Dependency Injection.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Handles all values managed by the Salt Framework. Enables "Inversion of Control" and therefore Dependency Injection.`Container()` |

### Functions

| Name | Summary |
|---|---|
| [addElement](add-element.md) | Adds an element to the container with a name to associate it.`fun addElement(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, instance: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds an element to the container. The classname will be the name associated with the element.`fun addElement(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Adds an element to the container. Instead of a concrete instance, the element will be generated from its classname with the default constructor. The classname will be the name associated with the element.`fun addElement(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getElement](get-element.md) | Returns an element through its associated name or classname.`fun getElement(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |

### Companion Object Functions

| Name | Summary |
|---|---|
| [getInstance](get-instance.md) | Returns an element through its associated name or classname from a given container.`fun getInstance(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, container: `[`Container`](./index.md)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?` |
