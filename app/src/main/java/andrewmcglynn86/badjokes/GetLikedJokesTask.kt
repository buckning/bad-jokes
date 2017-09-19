package andrewmcglynn86.badjokes

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.widget.Button

/**
 * Created by amcglynn on 19/09/2017.
 */
class GetLikedJokesTask(var context: Context) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        var dbHelper = DBHelper(context)

        val db = dbHelper.readableDatabase

        val cursor = db.query(
                "joke", arrayOf("joke_text"), null, null, null, null, null)

        val itemIds = ArrayList<String>()
        while (cursor.moveToNext()) {
            val itemId = cursor.getString(0)
            println("found " + itemId)
            itemIds.add(itemId)
        }
        cursor.close()


        return 1L
    }

    override fun onPreExecute() {
        super.onPreExecute()

    }

    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)
    }
}