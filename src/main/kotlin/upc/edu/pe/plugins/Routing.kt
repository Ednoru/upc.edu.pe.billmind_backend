package upc.edu.pe.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import upc.edu.pe.routes.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Aca no hay nada, ve a /v1/clients o /v1/debts, etc")
        }
        clientRouting()
        debtRouting()
        cardRouting()
        subscriptionRouting()
        reminderRouting()
    }
}
