[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [WebSecurityConfigurator](./index.md)

# WebSecurityConfigurator

`interface WebSecurityConfigurator` [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/security/WebSecurityConfig.kt#L31)

Interface that every class marked with [WebSecurity](../../pass.salt.code.annotations/-web-security/index.md) should implement to configure Salt security.

Example usage:

``` kotlin
@WebSecurity
class SecTest: WebSecurityConfigurator {

    override fun authenticate(username: String, password: String): Boolean {
        return username == "admin" && password == "admin"
    }

    override fun configure(conf: WebSecurityConfig) {
        conf
            .matchRequests("/hello", "/css/login.css", "/favicon.ico", "/register").permitAll()
            .anyRequest().authenticated()
            .loginPath("/login")
            .logoutPath("/logout")
            .successfulLoginPath("/")
    }
}
```

### Functions

| Name | Summary |
|---|---|
| [authenticate](authenticate.md) | Configures authentication process.`abstract fun authenticate(username: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, password: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [configure](configure.md) | Configures handling of request mappings.`abstract fun configure(conf: `[`WebSecurityConfig`](../-web-security-config/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
