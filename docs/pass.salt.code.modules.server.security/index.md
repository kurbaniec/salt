[salt](../index.md) / [pass.salt.code.modules.server.security](./index.md)

## Package pass.salt.code.modules.server.security

Contains the security-system of the server module.

### Types

| Name | Summary |
|---|---|
| [SaltSecurity](-salt-security/index.md) | Handles all security functionality of Salt. This includes authentication and authentication of requests.`class SaltSecurity : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |
| [SecurityScan](-security-scan/index.md) | Scans classes for security configurations. This classes are annotated with the [WebSecurity](../pass.salt.code.annotations/-web-security/index.md) annotation and implement the [WebSecurityConfigurator](-web-security-configurator/index.md) interface.`class SecurityScan : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |
| [SessionUser](-session-user/index.md) | Contains the client information for the session at runtime.`class SessionUser` |
| [WebSecurityConfig](-web-security-config/index.md) | Configures authentication on request paths. Also configures login/logout and success path.`class WebSecurityConfig` |
| [WebSecurityConfigurator](-web-security-configurator/index.md) | Interface that every class marked with [WebSecurity](../pass.salt.code.annotations/-web-security/index.md) should implement to configure Salt security.`interface WebSecurityConfigurator` |
