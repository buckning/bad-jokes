package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.db.DBHelper
import android.content.Context
import android.os.AsyncTask
import android.content.ContentValues
import android.widget.Button


/**
 * Created by amcglynn on 19/09/2017.
 * Task used to write the joke to the DB to say it was liked. Once completed, it changes the color
 * of the like button
 */
class LikeJokeTask(var button: Button, var context: Context, val joke: Joke) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        var dbHelper = DBHelper(context)

        if(joke.status == 200) {
            val db = dbHelper.getWritableDatabase()

            val values = ContentValues()
            values.put("joke_text", joke.joke)
            values.put("online_joke_id", joke.id)

            return db.insert("joke", null, values)
        }
        println("not saving joke " + joke)
        return -1
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)
        if(result != -1L) {
        }
    }
}
