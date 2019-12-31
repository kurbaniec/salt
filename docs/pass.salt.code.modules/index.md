[salt](../index.md) / [pass.salt.code.modules](./index.md)

## Package pass.salt.code.modules

Contains all the Salt modules.

### Types

| Name | Summary |
|---|---|
| [AutowiredScan](-autowired-scan/index.md) | Scans classes and injects values to the class properties from the [Container](../pass.salt.code.container/-container/index.md) if the property contains the [Autowired](../pass.salt.code.annotations/-autowired/index.md) annotation.`class AutowiredScan : `[`SaltProcessor`](-salt-processor/index.md) |
| [ComponentScan](-component-scan/index.md) | Scans classes and adds them to the [Container](../pass.salt.code.container/-container/index.md) if they contain the [Scan](../pass.salt.code.annotations/-scan/index.md) annotation.`class ComponentScan : `[`SaltProcessor`](-salt-processor/index.md) |
| [ModuleNotFound](-module-not-found/index.md) | Dummy class that is created when an unknown module is requested.`class ModuleNotFound : `[`SaltProcessor`](-salt-processor/index.md) |
| [SaltProcessor](-salt-processor/index.md) | Defines an interface that all Salt modules are build on. A module can be used as a single module or class module. A single module enables functionality through itself. A class module enables functionality through processing classes.`interface SaltProcessor` |
| [SaltThreadPool](-salt-thread-pool/index.md) | Representation of an executor service that initializes a concrete one from a given type.`class SaltThreadPool` |
| [SaltThreadPoolFactory](-salt-thread-pool-factory/index.md) | Initializes executor service and thread pool for Salt.`class SaltThreadPoolFactory : `[`SaltProcessor`](-salt-processor/index.md) |

### Properties

| Name | Summary |
|---|---|
| [logger](logger.md) | `val logger: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
