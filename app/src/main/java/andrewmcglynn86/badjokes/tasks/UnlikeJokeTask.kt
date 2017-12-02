package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.os.AsyncTask

class UnlikeJokeTask (var jokeService: JokeService, val joke: Joke) : AsyncTask<Void, Void, Long>() {

    override fun doInBackground(vararg params: Void?): Long? {
        jokeService.unlikeJoke(joke)
        return 1
    }
}
