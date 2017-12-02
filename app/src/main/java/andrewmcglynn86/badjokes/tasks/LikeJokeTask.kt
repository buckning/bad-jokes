package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.os.AsyncTask

class LikeJokeTask(var jokeService: JokeService, val joke: Joke) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        if(joke.status == 200) {
            jokeService.likeJoke(joke)
            return 1
        }
        return -1
    }
}
