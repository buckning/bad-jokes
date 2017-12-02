package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.os.AsyncTask

/**
 * Created by amcglynn on 15/09/2017.
 */
class GetBadJokeTask(var jokeService: JokeService) : AsyncTask<Void, Void, Joke>() {

    override fun doInBackground(vararg params: Void?): Joke? {
        return jokeService.getJoke()
    }
}
