package ktor

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async
import naoko.Library

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    val library = Library()

    routing {
        get("/"){
            val result = async {
                library.getResult()
            }
            proceed()
            call.respondText(result.await())
        }
    }
}
