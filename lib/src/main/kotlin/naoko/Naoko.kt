package naoko

import naoko.entities.enum.Country
import naoko.entities.enum.Language
import naoko.entities.json.news.News

class Naoko(private val config: NaokoConfig) {

    //通信を行うインスタンス
    private val naokoRepository = NaokoRepository(config.apiKey)


    //https://newsapi.org/docs/endpoints/everything
    suspend fun getEverything(
        q: String? = null,
        qInTitle: String? = null,
        sources: String? = null,
        domains: String? = null,
        excludeDomains: String? = null,
        from: String? = null,
        to: String? = null,
        language: Language? = null,
        sortBy: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): News {

        val parameters = mapOf(
            "q" to q,
            "qInTitle" to qInTitle,
            "sources" to sources,
            "domains" to domains,
            "excludeDomains" to excludeDomains,
            "from" to from,
            "to" to to,
            "language" to language?.value,
            "sortBy" to sortBy,
            "pageSize" to pageSize?.toString(),
            "page" to page?.toString()
        )

        return naokoRepository.getEverything(parameters)
    }


    //https://newsapi.org/docs/endpoints/sources
    suspend fun getSources(
        category: String? = null,
        language: String? = null,
        country: String? = null
    ): News {
        val parameters = mapOf(
            "country" to country,
            "language" to language,
            "category" to category,
        )

        return naokoRepository.getSources(parameters)
    }


    //https://newsapi.org/docs/endpoints/top-headlines
    suspend fun getTopHeadlines(
        country: String? = config.country.value,
        category: String? = null,
        q: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): News {

        val parameters = mapOf(
            "country" to country,
            "category" to category,
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
    ): News {
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
