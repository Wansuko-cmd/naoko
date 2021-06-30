package naoko.exception


/**
 * message: 考えられるエラー原因
 * response: News API側が返してきたJson
 */
class NaokoException(
    override val message: String,
    val response: String,
    val status: NaokoExceptionStatus
    ) : Exception()

enum class NaokoExceptionStatus {
    RESPONSE_400,
    RESPONSE_401,
    RESPONSE_429,
    RESPONSE_500,

    SERIALIZER_CATEGORY,
    SERIALIZER_COUNTRY,
    SERIALIZER_LANGUAGE,
    SERIALIZER_SORTBY,

    UNDEFINED
}
