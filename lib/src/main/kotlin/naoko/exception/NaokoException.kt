package naoko.exception


/**
 * message: 考えられるエラー原因
 * response: News API側が返してきたJson
 * status: エラーの内容
 */
class NaokoException(
    override val message: String,
    val response: String,
    val status: NaokoExceptionStatus
    ) : Exception()

enum class NaokoExceptionStatus {

    //Enumのserializer関連
    SERIALIZER_CATEGORY,
    SERIALIZER_COUNTRY,
    SERIALIZER_LANGUAGE,
    SERIALIZER_SORTBY,

    //HTTP通信関連
    RESPONSE_400,
    RESPONSE_401,
    RESPONSE_429,
    RESPONSE_500,

    //分類されない（HTTP通信部分）
    UNDEFINED
}
