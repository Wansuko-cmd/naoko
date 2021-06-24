package naoko

import naoko.entities.json.news.News

class Naoko(private val config: NaokoConfig) {

    private val naokoRepository = NaokoRepository(config.apiKey)


    suspend fun getTopHeadlines(
        country: String? = config.country,
        category: String? = null,
        q: String? = null,
        pageSize: Int? = null,
        page: Int? = null
    ): News {

        val parameters = mapOf(
            "country" to (country),
            "category" to category,
            "q" to q,
            "pageSize" to pageSize?.toString(),
            "page" to page?.toString()
        )

        return naokoRepository.getTopHeadlines(parameters)
    }

    companion object{
        fun build(apiKey: String, country: String = "us"): Naoko{
            val config = NaokoConfig(apiKey, country)
            return Naoko(config)
        }
    }
}
