package naoko.entities.enum

enum class Category(val value: String) {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");

    companion object{
        fun serializer(category: String) = when(category){
            "business" -> BUSINESS
            "entertainment" -> ENTERTAINMENT
            "general" -> GENERAL
            "health" -> HEALTH
            "science" -> SCIENCE
            "sports" -> SPORTS
            "technology" -> TECHNOLOGY

            else -> throw Exception("No Category Found witch correspond to $category")
        }
    }
}
