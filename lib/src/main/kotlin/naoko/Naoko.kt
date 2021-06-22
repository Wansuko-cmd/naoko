package naoko

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import naoko.entities.json.news.News

class Naoko(private val config: NaokoConfig) {

    private val client = HttpClient(CIO){
        install(JsonFeature){
            serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                }
            )
        }
    }

    suspend fun getResult(country: String? = null): News = withContext(Dispatchers.IO) {

        //動作確認をするときはi10janのurlを入れる
        return@withContext client.get<News>(
            "https://newsapi.org/v2/top-headlines?country=${country ?:config.country}&apiKey=${config.apiKey}"
        )
    }

    companion object{
        fun build(apiKey: String, country: String = "jp"): Naoko{
            val config = NaokoConfig(apiKey, country)
            return Naoko(config)
        }
    }
}
