package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.os.AsyncTask

class GetLikedJokesTask(var jokeService: JokeService) : AsyncTask<Void, Void, ArrayList<Joke>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<Joke>? {
        return jokeService.getAllLikedJokes()
    }
}
