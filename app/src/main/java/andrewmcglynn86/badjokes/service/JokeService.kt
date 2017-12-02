package andrewmcglynn86.badjokes.service

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.db.JokeDb
import andrewmcglynn86.badjokes.dto.Joke

class JokeService (val onlineRepository: OnlineJokeRepository, var jokeDb: JokeDb){

    fun getJoke() : Joke {
        return try {
            onlineRepository.getJoke()
        } catch (ex: Exception) {
            Joke("0000000", "Could not load joke", 500)
        }
    }

    fun likeJoke(joke: Joke) {
        jokeDb.saveJoke(joke)
    }

    fun unlikeJoke(joke: Joke) {
        jokeDb.deleteJoke(joke)
    }

    fun isJokeLiked(joke: Joke) : Boolean {
        return jokeDb.jokeExists(joke)
    }
}
