package ktor

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ktor.plugins.*

fun main() {
    embeddedServer(Netty, port = 8000, host = "127.0.0.1") {
        configureRouting()
    }.start(wait = true)
}
