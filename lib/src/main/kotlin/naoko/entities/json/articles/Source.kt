package naoko.entities.json.articles

import kotlinx.serialization.Serializable

@Serializable
data class Source(
    val id: String?,
    val name: String
)
