package naoko.entities.json.articles

import kotlinx.serialization.Serializable

@Serializable
data class NaokoArticles(
    val status: String,
    val totalResults: Int? = null,
    val articles: List<Article>? = null
)
