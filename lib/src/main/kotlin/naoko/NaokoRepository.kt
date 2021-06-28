package naoko

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.entities.json.articles.NaokoArticles
import naoko.entities.json.sources.NaokoSources
import javax.naming.AuthenticationException

internal class NaokoRepository(private val apiKey: String) {

    private val client = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
    }



    suspend fun getEverything(parameters: Map<String, String?>): NaokoArticles = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<NaokoArticles>(
            "https://newsapi.org/v2/everything"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }



    suspend fun getSources(parameters: Map<String, String?>): NaokoSources = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<NaokoSources>(
            "https://newsapi.org/v2/sources"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach{ (key, value) ->
                parameter(key, value)
            }
        }
    }



    suspend fun getTopHeadlines(parameters: Map<String, String?>): NaokoArticles = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<NaokoArticles>(
            "https://newsapi.org/v2/top-headlines"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }



    //API通信の際のエラーハンドラーをセットする関数
    private suspend fun <T> withAPIErrorHandler(block: suspend () -> T): T = withContext(Dispatchers.IO) {

        try {
            return@withContext block()

        }catch (e: ClientRequestException){
            val message = when(e.response.status.value){
                400 -> "The request was unacceptable, often due to a missing or misconfigured parameter."
                401 -> "You may miss your api key."
                429 -> "You made too many requests within a window of time and have been rate limited. Back off for a while."
                500 -> "An error may occurred on the News API server."
                else -> "Status Code: ${e.response.status.value}, News API return: ${e.response.readText()}"
            }
            throw NaokoException(message, e.response.readText())
        }
    }
}
