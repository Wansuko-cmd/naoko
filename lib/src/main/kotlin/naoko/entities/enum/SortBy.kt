package naoko.entities.enum

import naoko.NaokoException

enum class SortBy(val value: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("published_at");

    companion object{
        fun serializer(sortBy: String): SortBy = when(sortBy){
            "relevancy" -> RELEVANCY
            "popularity" -> POPULARITY
            "published_at" -> PUBLISHED_AT
            else -> throw NaokoException(
                "No SortBy Found which correspond to $sortBy",
                "Respond by SortBy Enum"
            )
        }
    }
}
