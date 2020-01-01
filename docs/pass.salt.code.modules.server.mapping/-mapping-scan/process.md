[salt](../../index.md) / [pass.salt.code.modules.server.mapping](../index.md) / [MappingScan](index.md) / [process](./process.md)

# process

`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/mapping/MappingScan.kt#L24)

Scans a class and looks if its marked with [Controller](../../pass.salt.code.annotations/-controller/index.md).
If yes, then it looks afters [Get](../../pass.salt.code.annotations/-get/index.md) and [Post](../../pass.salt.code.annotations/-post/index.md) mappings that are added to the [PepperServer](../../pass.salt.code.modules.server/-pepper-server/index.md).

