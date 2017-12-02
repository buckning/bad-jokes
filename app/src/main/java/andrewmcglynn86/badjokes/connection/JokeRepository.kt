package andrewmcglynn86.badjokes.connection

import andrewmcglynn86.badjokes.dto.Joke

/**
 * Created by amcglynn on 02/12/2017.
 */
interface JokeRepository {
    fun getJoke() : Joke
}
