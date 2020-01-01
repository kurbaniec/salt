package pass.salt.code.modules.server

import java.io.PrintWriter

/**
 * Enables easy building of HTTP responses in Salt.
 */
class HTTPTransport(
    val header: Header,
    val body: Body
) {
    class Header(vararg var header: String)
    class Body(vararg var body: String)

    constructor(header: Header) : this(header, Body())

    constructor(body: Body): this(Header(), Body())

    constructor(): this(Header(), Body())

    /**
     * Sends builded response.
     */
    fun transport(out: PrintWriter) {
        for (el in header.header) {
            out.println(el)
        }
        out.println()
        out.flush()
        for (el in body.body) {
            out.println(el)
        }
        out.flush()
    }

    /**
     * Build 200 - OK response.
     */
    fun do200(): HTTPTransport {
        return this.ok()
    }

    /**
     * Build 200 - Ok response.
     */
    fun ok(): HTTPTransport {
        var msg = ""
        for ((i, el) in body.body.withIndex()) {
            msg += if (i != body.body.size-1) {
                "$el&"
            } else el
        }
        val h = mutableListOf<String>()
        h.add("HTTP/1.1 200 OK")
        h.add("Content-Type: application/x-www-form-urlencoded")
        h.add("Content-Length: ${msg.length}")
        val b = mutableListOf<String>(msg)

        header.header = h.toTypedArray()
        body.body = b.toTypedArray()
        return this
    }

    /**
     * Build 403 - Forbidden response.
     */
    fun do403(): HTTPTransport {
        return this.forbidden()
    }

    /**
     * Build 403 - Forbidden response.
     */
    fun forbidden(): HTTPTransport {
        var msg = ""
        for ((i, el) in body.body.withIndex()) {
            msg += if (i != body.body.size-1) {
                "$el&"
            } else el
        }
        val h = mutableListOf<String>()
        h.add("HTTP/1.1 403 Forbidden")
        h.add("Content-Type: application/x-www-form-urlencoded")
        h.add("Content-Length: ${msg.length}")
        val b = mutableListOf<String>(msg)

        header.header = h.toTypedArray()
        body.body = b.toTypedArray()
        return this
    }

    /**
     * Build 424 - Failed Dependency response.
     */
    fun do424(): HTTPTransport {
        return this.failedDependency()
    }

    /**
     * Build 424 - Failed Dependency response.
     */
    fun failedDependency(): HTTPTransport {
        var msg = ""
        for ((i, el) in body.body.withIndex()) {
            msg += if (i != body.body.size-1) {
                "$el&"
            } else el
        }
        val h = mutableListOf<String>()
        h.add("HTTP/1.1 424 Failed Dependency")
        h.add("Content-Type: application/x-www-form-urlencoded")
        h.add("Content-Length: ${msg.length}")
        val b = mutableListOf<String>(msg)

        header.header = h.toTypedArray()
        body.body = b.toTypedArray()
        return this
    }

    /**
     * Build 423 - Locked response.
     */
    fun do423(): HTTPTransport {
        return this.locked()
    }

    /**
     * Build 423 - Locked response.
     */
    fun locked(): HTTPTransport {
        var msg = ""
        for ((i, el) in body.body.withIndex()) {
            msg += if (i != body.body.size-1) {
                "$el&"
            } else el
        }
        val h = mutableListOf<String>()
        h.add("HTTP/1.1 423 Locked")
        h.add("Content-Type: application/x-www-form-urlencoded")
        h.add("Content-Length: ${msg.length}")
        val b = mutableListOf<String>(msg)

        header.header = h.toTypedArray()
        body.body = b.toTypedArray()
        return this
    }

}