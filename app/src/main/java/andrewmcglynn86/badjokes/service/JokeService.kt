package andrewmcglynn86.badjokes.service

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.db.JokeDb
import andrewmcglynn86.badjokes.dto.Joke

class JokeService (val onlineRepository: OnlineJokeRepository, var jokeDb: JokeDb){

    val MAX_JOKE_DISPLAY_LENGTH = 127

    fun getJoke() : Joke {
        return try {
            onlineRepository.getJoke()
        } catch (ex: Exception) {
            Joke("0000000", "Could not load joke", 500)
        }
    }

    fun getAllLikedJokes() : ArrayList<Joke> {
        return jokeDb.getAllJokes()
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

    fun getAllLikeJokesTrimmed(values: ArrayList<Joke>) : ArrayList<String> {
        var jokes = ArrayList<String> ()

        values.forEach {
            var jokeText = it.joke
            jokeText.replace('\n', ' ')
            if(jokeText.length > MAX_JOKE_DISPLAY_LENGTH) {
                jokeText = jokeText.substring(0, MAX_JOKE_DISPLAY_LENGTH - 3) + "..."
            }
            jokes.add(jokeText)
        }

        return jokes
    }

    fun updateLikedJokes() {
        println("reading more from DB...")
        val result = jokeDb.getJokes(20, 10)

        println(result)
    }
}
