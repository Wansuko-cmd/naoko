package naoko

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Naoko(private val apiKey: String) {

    private val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    suspend fun getResult(): Int = withContext(Dispatchers.IO){

        //動作確認をするときはi10janのurlを入れる
        val result = client.get<News>("https://newsapi.org/v2/top-headlines?country=jp&apiKey=$apiKey")
        return@withContext result.totalResults
    }

    companion object{
        fun build(apiKey: String): Naoko{
            return Naoko(apiKey)
        }
    }
}
