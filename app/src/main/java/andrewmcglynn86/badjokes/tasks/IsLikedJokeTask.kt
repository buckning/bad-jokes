package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.os.AsyncTask

class IsLikedJokesTask(var jokeService: JokeService, var joke: Joke) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean? {
        return jokeService.isJokeLiked(joke)
    }
}
