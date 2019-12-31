

Salt is a simple application framework that features an inversion of control container for usage with Kotlin.

### All Types

| Name | Summary |
|---|---|
|

##### [pass.salt.code.loader.parser.ArrayParser](../pass.salt.code.loader.parser/-array-parser/index.md)

Used for parsing Arrays.


|

##### [pass.salt.code.loader.parser.AttributeParser](../pass.salt.code.loader.parser/-attribute-parser/index.md)

Interfaces that defines parsing behaviour.
Also, includes a method for correct parsing of an given String.


|

##### [pass.salt.code.annotations.Autowired](../pass.salt.code.annotations/-autowired/index.md)

Used to mark a dependency injection, a value that should be filled
with a corresponding value from the Salt [Container](../pass.salt.code.container/-container/index.md).


|

##### [pass.salt.code.modules.AutowiredScan](../pass.salt.code.modules/-autowired-scan/index.md)

Scans classes and injects values to the class properties from the [Container](../pass.salt.code.container/-container/index.md) if the property contains the
[Autowired](../pass.salt.code.annotations/-autowired/index.md) annotation.


|

##### [pass.salt.code.annotations.Bean](../pass.salt.code.annotations/-bean/index.md)

Used to mark a function, that returns an object that should be
managed by the Salt [Container](../pass.salt.code.container/-container/index.md).


|

##### [pass.salt.code.loader.parser.BooleanParser](../pass.salt.code.loader.parser/-boolean-parser/index.md)

Used for parsing booleans.


|

##### [pass.salt.code.modules.ComponentScan](../pass.salt.code.modules/-component-scan/index.md)

Scans classes and adds them to the [Container](../pass.salt.code.container/-container/index.md) if they contain the [Scan](../pass.salt.code.annotations/-scan/index.md) annotation.


|

##### [pass.salt.code.loader.config.Config](../pass.salt.code.loader.config/-config/index.md)

Handles the configuration of the Salt framework.


|

##### [pass.salt.code.exceptions.ConfigException](../pass.salt.code.exceptions/-config-exception/index.md)


|

##### [pass.salt.code.container.Container](../pass.salt.code.container/-container/index.md)

Handles all values managed by the Salt Framework.
Enables "Inversion of Control" and therefore Dependency Injection.


|

##### [pass.salt.code.annotations.Controller](../pass.salt.code.annotations/-controller/index.md)

Marks a class as a Controller configurator for requests.


|

##### [pass.salt.code.exceptions.ExceptionsTools](../pass.salt.code.exceptions/-exceptions-tools/index.md)


|

##### [pass.salt.code.loader.parser.FloatParser](../pass.salt.code.loader.parser/-float-parser/index.md)

Used for parsing Floats.


|

##### [pass.salt.code.annotations.Get](../pass.salt.code.annotations/-get/index.md)

Mapping for GET-Request endpoint.
Only viable in classes marked with [Controller](../pass.salt.code.annotations/-controller/index.md).


|

##### [pass.salt.code.modules.server.mapping.HTTPMethod](../pass.salt.code.modules.server.mapping/-h-t-t-p-method/index.md)


|

##### [pass.salt.code.modules.server.HTTPTransport](../pass.salt.code.modules.server/-h-t-t-p-transport/index.md)


|

##### [pass.salt.code.loader.parser.IntegerParser](../pass.salt.code.loader.parser/-integer-parser/index.md)

Used for parsing Integers.


|

##### [pass.salt.code.exceptions.InvalidConfigTypeGivenException](../pass.salt.code.exceptions/-invalid-config-type-given-exception/index.md)

Is thrown, when the given attribute type does not match the type of the found attribute.


|

##### [pass.salt.code.exceptions.InvalidMappingParamException](../pass.salt.code.exceptions/-invalid-mapping-param-exception/index.md)


|

##### [pass.salt.code.exceptions.InvalidParameterCountInURL](../pass.salt.code.exceptions/-invalid-parameter-count-in-u-r-l/index.md)


|

##### [pass.salt.code.exceptions.InvalidSecurityConfigurationException](../pass.salt.code.exceptions/-invalid-security-configuration-exception/index.md)


|

##### [pass.salt.code.exceptions.KeyStoreNotFoundException](../pass.salt.code.exceptions/-key-store-not-found-exception/index.md)


|

##### [pass.salt.code.loader.Loader](../pass.salt.code.loader/-loader/index.md)

Loads the Salt application.


|

##### [pass.salt.code.exceptions.MainPackageNotFoundException](../pass.salt.code.exceptions/-main-package-not-found-exception/index.md)


|

##### [pass.salt.code.modules.server.mapping.Mapping](../pass.salt.code.modules.server.mapping/-mapping/index.md)


|

##### [pass.salt.code.modules.server.mapping.MappingScan](../pass.salt.code.modules.server.mapping/-mapping-scan/index.md)


|

##### [pass.salt.code.modules.server.webparse.Model](../pass.salt.code.modules.server.webparse/-model/index.md)


|

##### [pass.salt.code.modules.ModuleNotFound](../pass.salt.code.modules/-module-not-found/index.md)

Dummy class that is created when an unknown module is requested.


|

##### [pass.salt.code.annotations.MongoDB](../pass.salt.code.annotations/-mongo-d-b/index.md)

Marks a class as MongoRepo for MongoDB access.


|

##### [pass.salt.code.modules.db.mongo.MongoInit](../pass.salt.code.modules.db.mongo/-mongo-init/index.md)

Initializes the Salt application to work with MongoDB.


|

##### [pass.salt.code.exceptions.MongoInitExecption](../pass.salt.code.exceptions/-mongo-init-execption/index.md)

Is thrown, when connection to MongoDB cannot be established.


|

##### [pass.salt.code.modules.db.mongo.MongoRepo](../pass.salt.code.modules.db.mongo/-mongo-repo/index.md)

Defines basic CRUD-operations to interact with MongoDB.


|

##### [pass.salt.code.modules.db.mongo.MongoScan](../pass.salt.code.modules.db.mongo/-mongo-scan/index.md)

Scans classes for [MongoDB](../pass.salt.code.annotations/-mongo-d-b/index.md) annotations.
If a class contains it, a concrete [MongoRepo](../pass.salt.code.modules.db.mongo/-mongo-repo/index.md) will be initialized,
that contains the custom CRUD-operations from the scanned class.


|

##### [pass.salt.code.modules.db.mongo.MongoWrapper](../pass.salt.code.modules.db.mongo/-mongo-wrapper/index.md)

Defines the logic for the proxy that will be used with [MongoRepo](../pass.salt.code.modules.db.mongo/-mongo-repo/index.md).
That means all CRUD-operations will be invoked and processed through
this class.


|

##### [pass.salt.code.exceptions.NoSuchConfigException](../pass.salt.code.exceptions/-no-such-config-exception/index.md)

Is thrown, when the given attribute is not found in the Salt configuration file.


|

##### [pass.salt.code.annotations.Param](../pass.salt.code.annotations/-param/index.md)

Mapping for Parameters that are expected from the request.
Can be used on functions mapped with [Get](../pass.salt.code.annotations/-get/index.md) or [Post](../pass.salt.code.annotations/-post/index.md) annotations.
Only viable in classes marked with [Controller](../pass.salt.code.annotations/-controller/index.md).


|

##### [pass.salt.code.modules.server.PepperServer](../pass.salt.code.modules.server/-pepper-server/index.md)


|

##### [pass.salt.code.annotations.Post](../pass.salt.code.annotations/-post/index.md)

Mapping for POST-Request endpoint.
Only viable in classes marked with [Controller](../pass.salt.code.annotations/-controller/index.md).


|

##### [pass.salt.code.exceptions.ReflectionInstanceException](../pass.salt.code.exceptions/-reflection-instance-exception/index.md)


|

##### [pass.salt.code.SaltApplication](../pass.salt.code/-salt-application/index.md)

Initializes a new application that utilises the Salt framework.


|

##### [pass.salt.code.modules.SaltProcessor](../pass.salt.code.modules/-salt-processor/index.md)

Defines an interface that all Salt modules are build on.
A module can be used as a single module or class module.
A single module enables functionality through itself.
A class module enables functionality through processing classes.


|

##### [pass.salt.code.modules.server.security.SaltSecurity](../pass.salt.code.modules.server.security/-salt-security/index.md)


|

##### [pass.salt.code.modules.SaltThreadPool](../pass.salt.code.modules/-salt-thread-pool/index.md)

Representation of an executor service that initializes a concrete one from a given type.


|

##### [pass.salt.code.modules.SaltThreadPoolFactory](../pass.salt.code.modules/-salt-thread-pool-factory/index.md)

Initializes executor service and thread pool for Salt.


|

##### [pass.salt.code.annotations.Scan](../pass.salt.code.annotations/-scan/index.md)

Used to mark classes that should be configured with the Salt.


|

##### [pass.salt.code.modules.server.security.SecurityScan](../pass.salt.code.modules.server.security/-security-scan/index.md)


|

##### [pass.salt.code.modules.server.ServerMainThread](../pass.salt.code.modules.server/-server-main-thread/index.md)


|

##### [pass.salt.code.modules.server.ServerWorkerThread](../pass.salt.code.modules.server/-server-worker-thread/index.md)


|

##### [pass.salt.code.modules.server.security.SessionUser](../pass.salt.code.modules.server.security/-session-user/index.md)


|

##### [pass.salt.code.modules.server.encryption.SSLManager](../pass.salt.code.modules.server.encryption/-s-s-l-manager/index.md)


|

##### [pass.salt.code.loader.parser.StringParser](../pass.salt.code.loader.parser/-string-parser/index.md)

Used for parsing String.


|

##### [pass.salt.code.loader.parser.TOMLFile](../pass.salt.code.loader.parser/-t-o-m-l-file/index.md)

Parses a TOML-File from a given filename.


|

##### [pass.salt.code.loader.parser.TOMLObject](../pass.salt.code.loader.parser/-t-o-m-l-object/index.md)

Representation of a TOML-Object.


|

##### [pass.salt.code.loader.parser.TOMLParser](../pass.salt.code.loader.parser/-t-o-m-l-parser/index.md)

Parses a TOML-File with [TOMLFile](../pass.salt.code.loader.parser/-t-o-m-l-file/index.md) and features functions to
interact with the found data.


|

##### [pass.salt.code.modules.server.webparse.Webparse](../pass.salt.code.modules.server.webparse/-webparse/index.md)


|

##### [pass.salt.code.annotations.WebSecurity](../pass.salt.code.annotations/-web-security/index.md)

Marks a class as a security configurator.


|

##### [pass.salt.code.modules.server.security.WebSecurityConfig](../pass.salt.code.modules.server.security/-web-security-config/index.md)


|

##### [pass.salt.code.modules.server.security.WebSecurityConfigurator](../pass.salt.code.modules.server.security/-web-security-configurator/index.md)


|

##### [pass.salt.code.modules.server.webparse.WebTools](../pass.salt.code.modules.server.webparse/-web-tools/index.md)


