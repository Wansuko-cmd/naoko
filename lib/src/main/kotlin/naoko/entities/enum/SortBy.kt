package naoko.entities.enum

enum class SortBy(val value: String) {
    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("published_at");

    companion object{
        fun serializer(sortBy: String): SortBy = when(sortBy){
            "relevancy" -> RELEVANCY
            "popularity" -> POPULARITY
            "published_at" -> PUBLISHED_AT
            else -> throw Exception("No SortBy Found which correspond to $sortBy")
        }
    }
}
