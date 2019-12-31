[salt](../../index.md) / [pass.salt.code.modules.server](../index.md) / [HTTPTransport](./index.md)

# HTTPTransport

`class HTTPTransport` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/HTTPTransport.kt#L5)

### Types

| Name | Summary |
|---|---|
| [Body](-body/index.md) | `class Body` |
| [Header](-header/index.md) | `class Header` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `HTTPTransport(header: Header)`<br>`HTTPTransport(body: Body)`<br>`HTTPTransport()`<br>`HTTPTransport(header: Header, body: Body)` |

### Properties

| Name | Summary |
|---|---|
| [body](body.md) | `val body: Body` |
| [header](header.md) | `val header: Header` |

### Functions

| Name | Summary |
|---|---|
| [do200](do200.md) | `fun do200(): `[`HTTPTransport`](./index.md) |
| [do403](do403.md) | `fun do403(): `[`HTTPTransport`](./index.md) |
| [do423](do423.md) | `fun do423(): `[`HTTPTransport`](./index.md) |
| [do424](do424.md) | `fun do424(): `[`HTTPTransport`](./index.md) |
| [failedDependecy](failed-dependecy.md) | `fun failedDependecy(): `[`HTTPTransport`](./index.md) |
| [forbidden](forbidden.md) | `fun forbidden(): `[`HTTPTransport`](./index.md) |
| [locked](locked.md) | `fun locked(): `[`HTTPTransport`](./index.md) |
| [ok](ok.md) | `fun ok(): `[`HTTPTransport`](./index.md) |
| [transport](transport.md) | `fun transport(out: `[`PrintWriter`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
