package naoko.entities.enum

import naoko.exception.NaokoException
import naoko.exception.NaokoExceptionStatus

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

            else -> throw NaokoException(
                "No Category Found witch correspond to $category",
                "Response by Category Enum class",
                NaokoExceptionStatus.SERIALIZER_CATEGORY
            )
        }
    }
}
