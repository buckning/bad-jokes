package andrewmcglynn86.badjokes

import android.content.Context
import android.os.AsyncTask

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var context: Context) : AsyncTask<Void, Void, MutableList<JokeResponse>>() {
    override fun doInBackground(vararg params: Void?): MutableList<JokeResponse>? {
        var dbHelper = DBHelper(context)

        val db = dbHelper.readableDatabase

        var savedJokes = mutableListOf<JokeResponse>()

        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"), null, null, null, null, null)

        while (cursor.moveToNext()) {
            val jokeText = cursor.getString(0)
            val jokeId = cursor.getString(1)
            savedJokes.add(JokeResponse(jokeId, jokeText, 200))
        }
        cursor.close()

        return savedJokes
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: MutableList<JokeResponse>?) {
        super.onPostExecute(result)

        result?.forEach { str -> println("joke = " + str) }
    }
}