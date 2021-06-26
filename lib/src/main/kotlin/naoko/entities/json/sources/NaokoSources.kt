package naoko.entities.json.sources

import kotlinx.serialization.Serializable

@Serializable
data class NaokoSources(
    val status: String,
    val sources: List<Sources>
)
