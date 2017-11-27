package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.dto.JokeResponse
import andrewmcglynn86.badjokes.db.DBHelper
import android.content.Context
import android.os.AsyncTask
import android.widget.Button

class UnlikeJokeTask (var button: Button, var context: Context, val joke: JokeResponse) : AsyncTask<Void, Void, Long>() {

    override fun doInBackground(vararg params: Void?): Long? {
        var dbHelper = DBHelper(context)
        val db = dbHelper.getWritableDatabase()
        db.delete("joke", "online_joke_id = ?", arrayOf(joke.id))
        return 1
    }

    override fun onPostExecute(result: Long?) {
        super.onPostExecute(result)

        if(result == 1L) {

        }
    }
}
