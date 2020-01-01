[salt](../../index.md) / [pass.salt.code.modules.server](../index.md) / [ServerWorkerThread](./index.md)

# ServerWorkerThread

`class ServerWorkerThread<P : `[`ServerSocket`](https://docs.oracle.com/javase/6/docs/api/java/net/ServerSocket.html)`, S : `[`Socket`](https://docs.oracle.com/javase/6/docs/api/java/net/Socket.html)`> : `[`Runnable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Runnable.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/ServerWorkerThread.kt#L28)

Represents a worker that handles the connection and operations with one client.

### Types

| Name | Summary |
|---|---|
| [Request](-request/index.md) | Simple class that represents a client request.`data class Request` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | Represents a worker that handles the connection and operations with one client.`ServerWorkerThread(socket: S, server: `[`ServerMainThread`](../-server-main-thread/index.md)`<P>, config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md)`, security: `[`Pair`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)`<`[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`, `[`SaltSecurity`](../../pass.salt.code.modules.server.security/-salt-security/index.md)`?>)` |

### Properties

| Name | Summary |
|---|---|
| [config](config.md) | `val config: `[`Config`](../../pass.salt.code.loader.config/-config/index.md) |
| [data](data.md) | `val data: `[`BufferedOutputStream`](https://docs.oracle.com/javase/6/docs/api/java/io/BufferedOutputStream.html) |
| [FILE_NOT_FOUND](-f-i-l-e_-n-o-t_-f-o-u-n-d.md) | `val FILE_NOT_FOUND: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [inp](inp.md) | `val inp: `[`BufferedReader`](https://docs.oracle.com/javase/6/docs/api/java/io/BufferedReader.html) |
| [listening](listening.md) | `var listening: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [log](log.md) | `val log: `[`Logger`](https://docs.oracle.com/javase/6/docs/api/java/util/logging/Logger.html)`!` |
| [METHOD_NOT_SUPPORTED](-m-e-t-h-o-d_-n-o-t_-s-u-p-p-o-r-t-e-d.md) | `val METHOD_NOT_SUPPORTED: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [out](out.md) | `val out: `[`PrintWriter`](https://docs.oracle.com/javase/6/docs/api/java/io/PrintWriter.html) |
| [sec](sec.md) | `var sec: `[`SaltSecurity`](../../pass.salt.code.modules.server.security/-salt-security/index.md)`?` |
| [secOn](sec-on.md) | `var secOn: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [server](server.md) | `val server: `[`ServerMainThread`](../-server-main-thread/index.md)`<P>` |
| [socket](socket.md) | `val socket: S` |
| [WEB_ROOT](-w-e-b_-r-o-o-t.md) | `val WEB_ROOT: `[`File`](https://docs.oracle.com/javase/6/docs/api/java/io/File.html) |

### Functions

| Name | Summary |
|---|---|
| [run](run.md) | Listen for client request and serve corresponding files or data.`fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [shutdown](shutdown.md) | Shutdown server worker.`fun shutdown(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
