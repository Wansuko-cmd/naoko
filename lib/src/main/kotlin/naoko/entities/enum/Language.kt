package naoko.entities.enum

import naoko.exception.NaokoException
import naoko.exception.NaokoExceptionStatus
import java.util.*

enum class Language(val value: String) {
    AR("ar"),
    DE("de"),
    EN("en"),
    ES("es"),
    FR("fr"),
    HE("he"),
    IT("it"),
    NL("nl"),
    NO("no"),
    PT("pt"),
    RU("ru"),
    SE("se"),
    UD("ud"),
    ZH("zh");

    companion object{
        fun serializer(language: String) = when(language.lowercase(Locale.getDefault())) {
            "ar" -> AR
            "de" -> DE
            "en" -> EN
            "es" -> ES
            "fr" -> FR
            "he" -> HE
            "it" -> IT
            "nl" -> NL
            "no" -> NO
            "pt" -> PT
            "ru" -> RU
            "se" -> SE
            "ud" -> UD
            "zh" -> ZH

            else -> NaokoException(
                "No Language Found which correspond to $language",
                "Respond by Language Enum",
                NaokoExceptionStatus.SERIALIZER_LANGUAGE
             )
        }
    }
}
