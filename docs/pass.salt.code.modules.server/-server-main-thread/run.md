[salt](../../index.md) / [pass.salt.code.modules.server](../index.md) / [ServerMainThread](index.md) / [run](./run.md)

# run

`fun run(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) [(source)](https://github.com/kurbaniec-tgm/salt/tree/master/code/modules/server/ServerMainThread.kt#L31)

Start server main thread.
If it is the https server, an [SSLServerSocket](https://docs.oracle.com/javase/6/docs/api/javax/net/ssl/SSLServerSocket.html) is used,
which uses the configured keystore from the [SSLManager](../../pass.salt.code.modules.server.encryption/-s-s-l-manager/index.md).

