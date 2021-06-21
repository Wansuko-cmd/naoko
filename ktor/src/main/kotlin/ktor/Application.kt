package ktor

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.async
import naoko.Library
import naoko.Naoko

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val apiKey = appConfig.property("news_api_key").getString()

    val library = Naoko.build(apiKey)

    routing {
        get("/"){
            val result = async {
                library.getResult()
            }
            proceed()
            call.respondText(result.await().toString())
        }
    }
}
