package ktor.plugins

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*
import naoko.Library

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText(Library().someLibraryMethod().toString())
        }
    }

}
