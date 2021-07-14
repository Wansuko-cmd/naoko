package naoko.entities.enum

import naoko.exception.NaokoException
import naoko.exception.NaokoExceptionStatus
import java.util.*

enum class SortBy(val value: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("published_at");

    companion object{
        fun serializer(sortBy: String): SortBy = when(sortBy.lowercase(Locale.getDefault())){
            "relevancy" -> RELEVANCY
            "popularity" -> POPULARITY
            "published_at" -> PUBLISHED_AT
            "publishedat" -> PUBLISHED_AT
            else -> throw NaokoException(
                "No SortBy Found which correspond to $sortBy",
                "Respond by SortBy Enum",
                NaokoExceptionStatus.SERIALIZER_SORTBY
            )
        }
    }
}
