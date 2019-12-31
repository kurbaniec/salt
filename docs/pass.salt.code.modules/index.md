[salt](../index.md) / [pass.salt.code.modules](./index.md)

## Package pass.salt.code.modules

Contains all the Salt modules.

### Types

| Name | Summary |
|---|---|
| [AutowiredScan](-autowired-scan/index.md) | `class AutowiredScan : `[`SaltProcessor`](-salt-processor/index.md) |
| [ComponentScan](-component-scan/index.md) | `class ComponentScan : `[`SaltProcessor`](-salt-processor/index.md) |
| [ModuleNotFound](-module-not-found/index.md) | `class ModuleNotFound : `[`SaltProcessor`](-salt-processor/index.md) |
| [SaltProcessor](-salt-processor/index.md) | Defines an interface that all Salt modules are build on. A module can be used as a single module or class module. A single module enables functionality through itself. A class module enables functionality through processing classes.`interface SaltProcessor` |
| [SaltThreadPool](-salt-thread-pool/index.md) | `class SaltThreadPool` |
| [SaltThreadPoolFactory](-salt-thread-pool-factory/index.md) | `class SaltThreadPoolFactory : `[`SaltProcessor`](-salt-processor/index.md) |

### Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
