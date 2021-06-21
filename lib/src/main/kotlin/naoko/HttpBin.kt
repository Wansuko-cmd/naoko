package naoko

import kotlinx.serialization.Serializable

@Serializable
data class HttpBin(
    val origin: String
)
