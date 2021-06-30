package naoko.entities.enum

import naoko.exception.NaokoException
import naoko.exception.NaokoExceptionStatus

enum class Country(val value: String) {
    AE("ae"),
    AR("ar"),
    AT("at"),
    AU("au"),
    BE("be"),
    BG("bg"),
    BR("br"),
    CA("ca"),
    CH("ch"),
    CN("cn"),
    CO("co"),
    CU("cu"),
    CZ("cz"),
    DE("de"),
    EG("eg"),
    FR("fr"),
    GB("gb"),
    GR("gr"),
    HK("hk"),
    HU("hu"),
    ID("id"),
    IE("ie"),
    IL("il"),
    IN("in"),
    IT("it"),
    JP("jp"),
    KR("kr"),
    LT("lt"),
    LV("lv"),
    MA("ma"),
    MX("mx"),
    MY("my"),
    NG("ng"),
    NL("nl"),
    NO("no"),
    NZ("nz"),
    PH("ph"),
    PL("pl"),
    PT("pt"),
    RO("ro"),
    RS("rs"),
    RU("ru"),
    SA("sa"),
    SE("se"),
    SG("sg"),
    SI("si"),
    SK("sk"),
    TH("th"),
    TR("tr"),
    TW("tw"),
    UA("ua"),
    US("us"),
    VE("ve"),
    ZA("za");

    companion object{
        fun serializer(country: String): Country = when(country){
            "ae" -> AE
            "ar" -> AR
            "at" -> AT
            "au" -> AU
            "be" -> BE
            "bg" -> BG
            "br" -> BR
            "ca" -> CA
            "ch" -> CH
            "cn" -> CN
            "co" -> CO
            "cu" -> CU
            "cz" -> CZ
            "de" -> DE
            "eg" -> EG
            "fr" -> FR
            "gb" -> GB
            "gr" -> GR
            "hk" -> HK
            "hu" -> HU
            "id" -> ID
            "ie" -> IE
            "il" -> IL
            "in" -> IN
            "it" -> IT
            "jp" -> JP
            "kr" -> KR
            "lt" -> LT
            "lv" -> LV
            "ma" -> MA
            "mx" -> MX
            "my" -> MY
            "ng" -> NG
            "nl" -> NL
            "no" -> NO
            "nz" -> NZ
            "ph" -> PH
            "pl" -> PL
            "pt" -> PT
            "ro" -> RO
            "rs" -> RS
            "ru" -> RU
            "sa" -> SA
            "se" -> SE
            "sg" -> SG
            "si" -> SI
            "sk" -> SK
            "th" -> TH
            "tr" -> TR
            "tw" -> TW
            "ua" -> UA
            "us" -> US
            "ve" -> VE
            "za" -> ZA

            else  -> throw NaokoException(
                "No Country Found witch correspond to $country",
                "Respond by Country Enum class",
                NaokoExceptionStatus.SERIALIZER_COUNTRY
            )
        }
    }
}
