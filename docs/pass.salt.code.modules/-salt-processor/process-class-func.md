[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [SaltProcessor](index.md) / [processClassFunc](./process-class-func.md)

# processClassFunc

`fun <reified C : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, reified A : `[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`> processClassFunc(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Annotation`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-annotation/index.html)`, `[`KFunction`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-function/index.html)`<*>>>?` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/SaltProcessor.kt#L109)

Searches for specific functions annotated with annotation A in a class that is annotated with annotation C.
Found functions will be returned as Pairs with their annotation

