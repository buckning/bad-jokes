package andrewmcglynn86.badjokes

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.widget.Button

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var context: Context) : AsyncTask<Void, Void, MutableList<String>>() {
    override fun doInBackground(vararg params: Void?): MutableList<String>? {
        var dbHelper = DBHelper(context)

        val db = dbHelper.readableDatabase

        var savedJokes = mutableListOf<String>()

        val cursor = db.query(
                "joke", arrayOf("joke_text"), null, null, null, null, null)

        while (cursor.moveToNext()) {
            val itemId = cursor.getString(0)
            println("found " + itemId)
            savedJokes.add(itemId)
        }
        cursor.close()


        return savedJokes
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: MutableList<String>?) {
        super.onPostExecute(result)

        result?.forEach { str -> println("joke text = " + str) }
    }
}