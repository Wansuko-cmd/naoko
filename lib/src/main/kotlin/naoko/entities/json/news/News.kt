package naoko.entities.json.news

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val status: String,
    val totalResults: Int? = null,
    val articles: List<Article>? = null
)
