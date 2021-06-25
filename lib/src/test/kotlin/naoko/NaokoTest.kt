package naoko

import naoko.entities.enum.Country
import org.junit.Test

class NaokoTest {

    val naoko = Naoko.build(
        apiKey = Config.APIKEY.value,
        country = Country.JP
    )



    class GetTopHeadlinesTest {

        @Test
        fun normal(){
            println("The Value is: ${Config.APIKEY.value}")
        }
    }
}
