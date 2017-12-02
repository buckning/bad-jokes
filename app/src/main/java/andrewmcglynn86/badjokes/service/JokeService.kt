package andrewmcglynn86.badjokes.service

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.dto.Joke

/**
 * Created by amcglynn on 02/12/2017.
 */
class JokeService {
    fun getJoke(jokeUrl: String) : Joke {
        return OnlineJokeRepository(jokeUrl).getJoke()
    }
}
