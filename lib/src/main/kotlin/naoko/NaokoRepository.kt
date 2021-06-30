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
import naoko.exception.NaokoException
import naoko.exception.NaokoExceptionStatus

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
            val (message, status) = when(e.response.status.value){

                400 ->
                    Pair(
                        "The request was unacceptable, often due to a missing or misconfigured parameter.",
                        NaokoExceptionStatus.RESPONSE_400
                    )

                401 ->
                    Pair(
                        "You may miss your api key.",
                        NaokoExceptionStatus.RESPONSE_401
                    )

                429 ->
                    Pair(
                        "You made too many requests within a window of time and have been rate limited. Back off for a while.",
                        NaokoExceptionStatus.RESPONSE_429
                    )

                500 ->
                    Pair(
                        "An error may occurred on the News API server.",
                        NaokoExceptionStatus.RESPONSE_500
                    )

                else ->
                    Pair(
                        "Status Code: ${e.response.status.value}, News API return: ${e.response.readText()}",
                        NaokoExceptionStatus.UNDEFINED
                    )
            }
            throw NaokoException(message, e.response.readText(), status)
        }
    }
}
