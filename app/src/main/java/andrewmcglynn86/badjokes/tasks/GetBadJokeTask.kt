package andrewmcglynn86.badjokes.tasks

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.JokeManager
import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.activities.MainActivity
import andrewmcglynn86.badjokes.db.DBHelper
import andrewmcglynn86.badjokes.db.JokeDb
import android.content.Context
import android.os.AsyncTask
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by amcglynn on 15/09/2017.
 */
class GetBadJokeTask(var activity: MainActivity, var jokeManager: JokeManager,
                     var textBox: TextView, var refreshButton: Button,
                     var context: Context) : AsyncTask<Void, Void, Joke>() {

    var jokeFoundInDb = false

    override fun doInBackground(vararg params: Void?): Joke? {
        val jokeResponse = OnlineJokeRepository("https://icanhazdadjoke.com/").getJoke()

        var dbHelper = DBHelper(context)
        jokeFoundInDb = JokeDb().jokeExists(dbHelper, jokeResponse.id)

        return jokeResponse
    }

    override fun onPreExecute() {
        super.onPreExecute()
        refreshButton.setEnabled(false)
    }

    override fun onPostExecute(result: Joke?) {
        super.onPostExecute(result)
        textBox.setText(result?.joke)

        if(result?.status != 200) {
            Toast.makeText(activity, "Could not load joke", Toast.LENGTH_SHORT).show()
        }

        refreshButton.setEnabled(true)

        jokeManager.currentJoke = result!!
    }
}
