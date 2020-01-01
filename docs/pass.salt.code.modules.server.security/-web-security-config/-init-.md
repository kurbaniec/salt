[salt](../../index.md) / [pass.salt.code.modules.server.security](../index.md) / [WebSecurityConfig](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`WebSecurityConfig()`

Configures authentication on request paths. Also configures login/logout and success path.

If [permitAll](permit-all.md) is called on a [matchRequests](match-requests.md), the configuration is open, only mapped entries are secured.

If [authenticated](authenticated.md) is called on a [matchRequests](match-requests.md), the configuration is not open, mapped entries will not secured
but every other request.

