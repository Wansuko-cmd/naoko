package naoko

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val totalResults: Int
)
