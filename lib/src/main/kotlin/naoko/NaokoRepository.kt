package naoko

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.entities.json.news.News
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



    suspend fun getEverything(parameters: Map<String, String?>): News = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<News>(
            "https://newsapi.org/v2/everything"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }



    suspend fun getSources(parameters: Map<String, String?>): News = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<News>(
            "https://newsapi.org/v2/sources"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach{ (key, value) ->
                parameter(key, value)
            }
        }
    }



    suspend fun getTopHeadlines(parameters: Map<String, String?>): News = withAPIErrorHandler {

        return@withAPIErrorHandler client.get<News>(
            "https://newsapi.org/v2/top-headlines"
        ){
            parameter("apiKey", apiKey)

            parameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }



    //API通信の際のエラーハンドラーをセットする関数
    private suspend fun <T> withAPIErrorHandler(func: suspend () -> T): T = withContext(Dispatchers.IO) {

        try {
            return@withContext func()

        }catch (e: ClientRequestException){
            when(e.response.status.value){
//                400 -> TODO("The request was unacceptable, often due to a missing or misconfigured parameter.")
                401 -> throw AuthenticationException(
                    "You may miss your api key. News API return: ${e.response.readText()}"
                )
                429 -> TODO("制限オーバー")
                500 -> TODO("News API側がエラー起こした")
            }
            throw Exception("Status Code: ${e.response.status.value}, News API return: ${e.response.readText()}")
        }
    }
}
