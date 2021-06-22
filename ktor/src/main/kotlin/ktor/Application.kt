package ktor

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.Naoko

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val apiKey = appConfig.property("news_api_key").getString()

    val library = Naoko.build(
        apiKey = apiKey,
        country = "jp"
    )

    routing {
        get("/"){
            val result = withContext(Dispatchers.Default) {
                library.getResult()
            }
            call.respondText(result.toString())
        }

        get("/{country}"){
            val country = call.parameters["country"]
            val result = withContext(Dispatchers.Default){
                library.getResult(country)
            }
            call.respondText(result.code ?: result.status)
        }
    }
}
