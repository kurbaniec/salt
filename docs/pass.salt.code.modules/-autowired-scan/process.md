[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [AutowiredScan](index.md) / [process](./process.md)

# process

`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/AutowiredScan.kt#L16)

Scans a given class and injects values to the class properties from the [Container](../../pass.salt.code.container/-container/index.md) if the property contains the
[Autowired](../../pass.salt.code.annotations/-autowired/index.md) annotation.

