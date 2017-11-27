package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.db.DBHelper
import andrewmcglynn86.badjokes.db.JokeDb
import android.content.Context
import android.os.AsyncTask

/**
 * Created by amcglynn on 26/11/2017.
 */
class IsLikedJokesTask(var jokeId: String, var context: Context) : AsyncTask<Void, Void, Boolean>() {

    override fun doInBackground(vararg params: Void?): Boolean? {
        var dbHelper = DBHelper(context)
        return JokeDb().jokeExists(dbHelper, jokeId)
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }
}