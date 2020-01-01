[salt](../index.md) / [pass.salt.code.modules.server.mapping](./index.md)

## Package pass.salt.code.modules.server.mapping

Contains the request-mapping of the server module.

### Types

| Name | Summary |
|---|---|
| [HTTPMethod](-h-t-t-p-method/index.md) | Enum for alls supported http methods by Salt.`enum class HTTPMethod` |
| [Mapping](-mapping/index.md) | Stores all request mappings for a given [HTTPMethod](-h-t-t-p-method/index.md).`class Mapping` |
| [MappingScan](-mapping-scan/index.md) | Scans classes marked with [Controller](../pass.salt.code.annotations/-controller/index.md) to look after [Get](../pass.salt.code.annotations/-get/index.md) and [Post](../pass.salt.code.annotations/-post/index.md) mappings.`class MappingScan : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |

### Properties

| Name | Summary |
|---|---|
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
