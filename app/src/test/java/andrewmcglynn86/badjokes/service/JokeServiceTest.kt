package andrewmcglynn86.badjokes.service

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by amcglynn on 02/12/2017.
 */
class JokeServiceTest {

    @Mock
    lateinit var mockRepository: OnlineJokeRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testJokeServiceReturnsJokeWhenOnlineRepositoryCompletesSuccessfully() {
        JokeService(mockRepository).getJoke()
    }
}