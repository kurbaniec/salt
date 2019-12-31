[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [ComponentScan](./index.md)

# ComponentScan

`class ComponentScan : `[`SaltProcessor`](../-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/ComponentScan.kt#L10)

Scans classes and adds them to the [Container](../../pass.salt.code.container/-container/index.md) if they contain the [Scan](../../pass.salt.code.annotations/-scan/index.md) annotation.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Scans classes and adds them to the [Container](../../pass.salt.code.container/-container/index.md) if they contain the [Scan](../../pass.salt.code.annotations/-scan/index.md) annotation.`ComponentScan(config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Scans a given class and adds it to the [Container](../../pass.salt.code.container/-container/index.md) if it contains the [Scan](../../pass.salt.code.annotations/-scan/index.md) annotation.`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Not used.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
