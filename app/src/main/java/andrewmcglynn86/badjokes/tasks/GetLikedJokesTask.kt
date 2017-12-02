package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.activities.FavouritesActivity
import andrewmcglynn86.badjokes.db.DBHelper
import andrewmcglynn86.badjokes.db.JokeDb
import android.content.Context
import android.os.AsyncTask

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var favouritesActivity: FavouritesActivity, var context: Context) : AsyncTask<Void, Void, ArrayList<Joke>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<Joke>? {
        var dbHelper = DBHelper(context)
        return JokeDb().getAllJokes(dbHelper)
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: ArrayList<Joke>?) {
        super.onPostExecute(result)

        favouritesActivity.setDisplayList(result)
    }
}