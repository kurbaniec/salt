[salt](../index.md) / [pass.salt.code.modules.server](./index.md)

## Package pass.salt.code.modules.server

Server module of Salt.

### Types

| Name | Summary |
|---|---|
| [HTTPTransport](-h-t-t-p-transport/index.md) | Enables easy building of HTTP responses in Salt.`class HTTPTransport` |
| [PepperServer](-pepper-server/index.md) | Represents the Pepper webserver that is used by the Salt framework. Pepper consist of two [ServerMainThread](-server-main-thread/index.md)s that serve http and https.`class PepperServer : `[`SaltProcessor`](../pass.salt.code.modules/-salt-processor/index.md) |
| [ServerMainThread](-server-main-thread/index.md) | Main server thread that listens for http- or https client connections and spawns a [ServerWorkerThread](-server-worker-thread/index.md) for every new connection request.`class ServerMainThread<P : `[`ServerSocket`](https://docs.oracle.com/javase/6/docs/api/java/net/ServerSocket.html)`> : `[`Runnable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Runnable.html) |
| [ServerWorkerThread](-server-worker-thread/index.md) | Represents a worker that handles the connection and operations with one client.`class ServerWorkerThread<P : `[`ServerSocket`](https://docs.oracle.com/javase/6/docs/api/java/net/ServerSocket.html)`, S : `[`Socket`](https://docs.oracle.com/javase/6/docs/api/java/net/Socket.html)`> : `[`Runnable`](https://docs.oracle.com/javase/6/docs/api/java/lang/Runnable.html) |
