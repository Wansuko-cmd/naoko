package ktor

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.Naoko
import naoko.entities.enum.Category
import naoko.entities.enum.Country
import naoko.entities.enum.SortBy
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

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
                naoko.getTopHeadlines(
                    country = Country.US
                )
            }
            call.respondText(result.toString())
        }

        get("/top"){

            val result = withContext(Dispatchers.Default) {
                naoko.getTopHeadlines(
                    sources = "bbc-news"
//                    country = Country.JP,
//                    category = Category.BUSINESS,
//                    pageSize = 1,
//                    page = 3
                )
            }
            call.respondText(result.toString())
        }

        get("/top/{q}"){
            val q = call.parameters["q"]

            val result = withContext(Dispatchers.Default) {
                naoko.getTopHeadlines(
                    country = Country.JP,
                    category = Category.BUSINESS,
                    q = q,
                    pageSize = 100
                )
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
                naoko.getEverything(
                    qInTitle = q,
                    domains = listOf("techcrunch.com"),
                    to = LocalDateTime.parse("2021-06-15T21:30:50"),
                    sortBy = SortBy.RELEVANCY
                )
            }
            call.respondText(result.toString())
        }

        get("/sources"){
            val result = withContext(Dispatchers.Default) {
                naoko.getSources(

                )
            }
            call.respondText(result.toString())
        }

        get("/{country}"){
            val country = call.parameters["country"]
            val result = withContext(Dispatchers.Default){
                naoko.getTopHeadlines(
                    country = country?.let { Country.serializer(it) }
                )
            }
            call.respondText(result.toString())
        }
    }
}
