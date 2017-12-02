package andrewmcglynn86.badjokes.service

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.db.JokeDb
import andrewmcglynn86.badjokes.dto.Joke
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

/**
 * Created by amcglynn on 02/12/2017.
 */
class JokeServiceTest {

    @Test
    fun testJokeServiceReturnsJokeWhenOnlineRepositoryCompletesSuccessfully() {
        val mockJoke = Joke("6EYLBscN7wc", "This furniture store keeps emailing me, " +
                "all I wanted was one night stand!", 200)

        val mockDb = mock<JokeDb>()
        var mockRepository: OnlineJokeRepository = mock<OnlineJokeRepository> {
            on {
                getJoke()
            } doReturn (mockJoke)
        }

        val joke = JokeService(mockRepository, mockDb).getJoke()

        assertEquals(joke.status, 200)
        assertEquals(joke.id, "6EYLBscN7wc")
        assertEquals(joke.joke, "This furniture store keeps emailing me, " +
                "all I wanted was one night stand!")
    }

    @Test
    fun testJokeServiceReturnsErrorJokeMessageWhenOnlineRepositoryCannotBeReached() {
        val mockDb = mock<JokeDb>()
        var mockRepository: OnlineJokeRepository = mock<OnlineJokeRepository> {
            on {
                getJoke()
            } doThrow IOException("Failed to connect to repo")
        }

        val joke = JokeService(mockRepository, mockDb).getJoke()
        assertEquals(joke.status, 500)
        assertEquals(joke.id, "0000000")
        assertEquals(joke.joke, "Could not load joke")
    }
}
