[salt](../index.md) / [pass.salt.code.annotations](./index.md)

## Package pass.salt.code.annotations

### Annotations

| Name | Summary |
|---|---|
| [Autowired](-autowired/index.md) | Used to mark a dependency injection, a value that should be filled with a corresponding value from the Salt [Container](../pass.salt.code.container/-container/index.md).`annotation class Autowired` |
| [Bean](-bean/index.md) | Used to mark a function, that returns an object that should be managed by the Salt [Container](../pass.salt.code.container/-container/index.md).`annotation class Bean` |
| [Controller](-controller/index.md) | Marks a class as a Controller configurator for requests.`annotation class Controller` |
| [Get](-get/index.md) | Mapping for GET-Request endpoint. Only viable in classes marked with [Controller](-controller/index.md).`annotation class Get` |
| [MongoDB](-mongo-d-b/index.md) | Marks a class as MongoRepo for MongoDB access.`annotation class MongoDB` |
| [Param](-param/index.md) | Mapping for Parameters that are expected from the request. Can be used on functions mapped with [Get](-get/index.md) or [Post](-post/index.md) annotations. Only viable in classes marked with [Controller](-controller/index.md).`annotation class Param` |
| [Post](-post/index.md) | Mapping for POST-Request endpoint. Only viable in classes marked with [Controller](-controller/index.md).`annotation class Post` |
| [Scan](-scan/index.md) | Used to mark classes that should be configured with the Salt.`annotation class Scan` |
| [WebSecurity](-web-security/index.md) | Marks a class as a security configurator.`annotation class WebSecurity` |
