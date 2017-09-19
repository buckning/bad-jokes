package andrewmcglynn86.badjokes

import android.content.Context
import android.os.AsyncTask
import android.content.ContentValues
import android.graphics.Color
import android.widget.Button


/**
 * Created by amcglynn on 19/09/2017.
 * Task used to write the joke to the DB to say it was liked. Once completed, it changes the color
 * of the like button
 */
class LikeJokeTask(var button: Button, var context: Context, val jokeId: String) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        var dbHelper = DBHelper(context)

        val db = dbHelper.getWritableDatabase()

        val values = ContentValues()
        values.put("joke_text", jokeId)

        return db.insert("joke", null, values)
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)
        button.setBackgroundColor(Color.RED);
    }
}
