package naoko.entities.json.news

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String
)
