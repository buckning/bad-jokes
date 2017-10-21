package andrewmcglynn86.badjokes

import android.os.AsyncTask
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ToggleButton
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by amcglynn on 15/09/2017.
 */
class GetBadJokeTask(var activity: MainActivity, var jokeManager: JokeManager,
                     var textBox: TextView, var refreshButton: Button, var likeButton: ToggleButton) : AsyncTask<Void, Void, JokeResponse>() {

    override fun doInBackground(vararg params: Void?): JokeResponse? {
        var jokeUrl = URL("https://icanhazdadjoke.com/")
        var connection = jokeUrl.openConnection() as HttpURLConnection
        val jokeResponse = BadJoke(connection).getJoke()

        return jokeResponse
    }

    override fun onPreExecute() {
        super.onPreExecute()
        refreshButton.setEnabled(false)
    }

    override fun onPostExecute(result: JokeResponse?) {
        super.onPostExecute(result)
        textBox.setText(result?.joke)

        if(result?.status != 200) {
            Toast.makeText(activity, "Could not load joke", Toast.LENGTH_SHORT).show()
        }

        refreshButton.setEnabled(true)

        jokeManager.currentJoke = result!!
        likeButton.isChecked = false
    }
}
