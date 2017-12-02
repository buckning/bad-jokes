package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.db.JokeDb
import android.os.AsyncTask

class UnlikeJokeTask (var jokeDb: JokeDb, val joke: Joke) : AsyncTask<Void, Void, Long>() {

    override fun doInBackground(vararg params: Void?): Long? {
        jokeDb.deleteJoke(joke)
        return 1
    }
}
