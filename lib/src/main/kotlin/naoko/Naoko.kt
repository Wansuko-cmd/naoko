package naoko

import naoko.entities.enum.Category
import naoko.entities.enum.Country
import naoko.entities.enum.Language
import naoko.entities.enum.SortBy
import naoko.entities.json.articles.NaokoArticles
import naoko.entities.json.sources.NaokoSources
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Naoko(private val config: NaokoConfig) {

    //通信を行うインスタンス
    private val naokoRepository = NaokoRepository(config.apiKey)


    //https://newsapi.org/docs/endpoints/everything
    suspend fun getEverything(
        q: String? = null,
        qInTitle: String? = null,
        sources: String? = null,
        domains: List<String>? = null,
        excludeDomains: List<String>? = null,
        from: LocalDateTime? = null,
        to: LocalDateTime? = null,
        language: Language? = null,
        sortBy: SortBy? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): NaokoArticles {

        val parameters = mapOf(
            "q" to q,
            "qInTitle" to qInTitle,
            "sources" to sources,
            "domains" to domains?.reduce{ tmp, value -> "$tmp,$value" },
            "excludeDomains" to excludeDomains?.reduce{ tmp, value -> "$tmp,$value" },
            "from" to from?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "to" to to?.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            "language" to language?.value,
            "sortBy" to sortBy?.value,
            "pageSize" to pageSize?.toString(),
            "page" to page?.toString()
        )

        return naokoRepository.getEverything(parameters)
    }


    //https://newsapi.org/docs/endpoints/sources
    suspend fun getSources(
        category: Category? = null,
        language: Language? = null,
        country: Country? = null
    ): NaokoSources{
        val parameters = mapOf(
            "category" to category?.value,
            "language" to language?.value,
            "country" to country?.value
        )

        return naokoRepository.getSources(parameters)
    }


    //https://newsapi.org/docs/endpoints/top-headlines
    suspend fun getTopHeadlines(
        country: Country? = config.country,
        category: Category? = null,
        q: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): NaokoArticles {

        val parameters = mapOf(
            "country" to country?.value,
            "category" to category?.value,
            "q" to q,
            "pageSize" to pageSize?.toString(),
            "page" to page?.toString()
        )

        return naokoRepository.getTopHeadlines(parameters)
    }


    //https://newsapi.org/docs/endpoints/top-headlines
    suspend fun getTopHeadlines(
        sources: String,
        q: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): NaokoArticles {
        val parameters = mapOf(
            "sources" to sources,
            "q" to q,
            "pageSize" to pageSize?.toString(),
            "page" to page?.toString()
        )

        return naokoRepository.getEverything(parameters)
    }


    companion object{
        fun build(apiKey: String, country: Country = Country.US): Naoko {
            val config = NaokoConfig(apiKey, country)
            return Naoko(config)
        }
    }
}
