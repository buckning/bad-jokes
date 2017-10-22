package andrewmcglynn86.badjokes

import android.content.Context
import android.os.AsyncTask
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var favouritesActivity: FavouritesActivity, var context: Context) : AsyncTask<Void, Void, ArrayList<String>>() {

    override fun doInBackground(vararg params: Void?): ArrayList<String>? {
        var dbHelper = DBHelper(context)
        return JokeDb().getAllJokes(dbHelper)
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: ArrayList<String>?) {
        super.onPostExecute(result)

        if(result != null) {
            var array = result.toTypedArray()
            favouritesActivity.setDisplayList(array)
        }
    }
}
