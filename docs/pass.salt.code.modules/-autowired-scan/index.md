[salt](../../index.md) / [pass.salt.code.modules](../index.md) / [AutowiredScan](./index.md)

# AutowiredScan

`class AutowiredScan : `[`SaltProcessor`](../-salt-processor/index.md) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/AutowiredScan.kt#L10)

Scans classes and injects values to the class properties from the [Container](../../pass.salt.code.container/-container/index.md) if the property contains the
[Autowired](../../pass.salt.code.annotations/-autowired/index.md) annotation.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Scans classes and injects values to the class properties from the [Container](../../pass.salt.code.container/-container/index.md) if the property contains the [Autowired](../../pass.salt.code.annotations/-autowired/index.md) annotation.`AutowiredScan(container: `[`Container`](../../pass.salt.code.container/-container/index.md)`)` |

### Properties

| Name | Summary |
|---|---|
| [container](container.md) | `val container: `[`Container`](../../pass.salt.code.container/-container/index.md) |

### Functions

| Name | Summary |
|---|---|
| [process](process.md) | Scans a given class and injects values to the class properties from the [Container](../../pass.salt.code.container/-container/index.md) if the property contains the [Autowired](../../pass.salt.code.annotations/-autowired/index.md) annotation.`fun process(className: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Not used.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
