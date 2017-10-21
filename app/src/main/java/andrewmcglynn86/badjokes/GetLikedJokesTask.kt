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

        val db = dbHelper.readableDatabase

        var savedJokes = ArrayList<String>()

        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"), null, null, null, null, null)

        while (cursor.moveToNext()) {
            val jokeText = cursor.getString(0)
            savedJokes.add(jokeText)
        }
        cursor.close()

        return savedJokes
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
