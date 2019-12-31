[salt](../../index.md) / [pass.salt.code.container](../index.md) / [Container](index.md) / [addElement](./add-element.md)

# addElement

`fun addElement(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, instance: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/container/Container.kt#L20)

Adds an element to the container with a name to associate it.

`fun addElement(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/container/Container.kt#L33)

Adds an element to the container.
The classname will be the name associated with the element.

`fun addElement(name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/container/Container.kt#L45)

Adds an element to the container.
Instead of a concrete instance, the element will be generated from its
classname with the default constructor.
The classname will be the name associated with the element.

