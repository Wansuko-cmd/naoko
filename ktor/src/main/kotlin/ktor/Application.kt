package ktor

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.Naoko
import naoko.entities.enum.Country

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.main(){

    val appConfig = HoconApplicationConfig(ConfigFactory.load())
    val apiKey = appConfig.property("news_api_key").getString()

    val naoko = Naoko.build(
        apiKey = apiKey,
        country = Country.JP
    )

    routing {
        get("/"){
            val result = withContext(Dispatchers.Default) {
                naoko.getTopHeadlines()
            }
            call.respondText(result.toString())
        }

        get("/top"){
            val result = withContext(Dispatchers.Default) {
                naoko.getTopHeadlines()
            }
            call.respondText(result.toString())
        }

        get("/every") {
            val result = withContext(Dispatchers.Default) {
                naoko.getEverything()
            }
            call.respondText(result.toString())
        }

        get("/every/{q}"){
            val q = call.parameters["q"]
            val result = withContext(Dispatchers.Default) {
                naoko.getEverything(q = q)
            }
            call.respondText(result.toString())
        }

        get("/sources"){
            val result = withContext(Dispatchers.Default) {
                naoko.getSources()
            }
            call.respondText(result.toString())
        }

        get("/{country}"){
            val country = call.parameters["country"]
            val result = withContext(Dispatchers.Default){
                naoko.getTopHeadlines(country = country)
            }
            call.respondText(result.toString())
        }
    }
}
