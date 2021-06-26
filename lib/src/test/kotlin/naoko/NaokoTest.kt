package naoko

import kotlinx.coroutines.runBlocking
import naoko.entities.enum.Country
import org.junit.Test


class NaokoTest {

    val naoko = Naoko.build(
        apiKey = Config.APIKEY.value,
        country = Country.JP
    )


    class GetTopHeadlinesTest {


        private val naoko = Naoko.build(
            apiKey = Config.APIKEY.value,
            country = Country.JP
        )

        @Test
        fun normal() = runBlocking{
            println(naoko.getTopHeadlines())
        }
    }
}
