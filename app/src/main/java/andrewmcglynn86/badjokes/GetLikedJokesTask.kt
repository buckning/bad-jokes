package andrewmcglynn86.badjokes

import android.content.Context
import android.os.AsyncTask

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var favouritesActivity: FavouritesActivity, var context: Context) : AsyncTask<Void, Void, ArrayList<JokeResponse>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<JokeResponse>? {
        var dbHelper = DBHelper(context)
        return JokeDb().getAllJokes(dbHelper)
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: ArrayList<JokeResponse>?) {
        super.onPostExecute(result)

        favouritesActivity.setDisplayList(result)
    }
}
