package andrewmcglynn86.badjokes

import org.junit.Test
import org.mockito.Mockito.*
import org.junit.Assert.*
import java.io.IOException
import java.net.HttpURLConnection

/**
 * Created by amcglynn on 16/09/2017.
 */
class BadJokeTest {

    @Test
    fun testBadJokeReturnsErrorStringIfItCannotConnectToTheWebsite() {
        val httpUrlConnectionMock = mock(HttpURLConnection::class.java)

        `doThrow`(IOException("Mocked IO Exception")).`when`(httpUrlConnectionMock).connect()

        val badJoke = BadJoke(httpUrlConnectionMock)
        var joke = badJoke.getJoke()

        assertEquals("Could not load joke", joke)
    }
}
