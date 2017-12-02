package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.db.JokeDb
import android.os.AsyncTask

/**
 * Created by amcglynn on 19/09/2017.
 * Task used to write the joke to the DB to say it was liked. Once completed, it changes the color
 * of the like button
 */
class LikeJokeTask(var jokeDb: JokeDb, val joke: Joke) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        if(joke.status == 200) {
            jokeDb.saveJoke(joke)
            return 1
        }
        return -1
    }
}
