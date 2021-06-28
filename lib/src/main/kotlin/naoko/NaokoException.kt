package naoko


/**
 * message: 考えられるエラー原因
 * response: News API側が返してきたJson
 */
class NaokoException(message: String, response: String) : Exception()
