package upc.edu.pe

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import upc.edu.pe.plugins.*

fun main() {
    embeddedServer(
        Netty,
        port = 8080,
        host = "127.0.0.1",
        module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureDatabase()
}
