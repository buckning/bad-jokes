package andrewmcglynn86.badjokes

import android.os.AsyncTask
import android.widget.Button
import android.widget.TextView
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by amcglynn on 15/09/2017.
 */
class GetBadJokeTask(var textBox: TextView, var refreshButton: Button) : AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg params: Void?): String? {
        var jokeUrl = URL("https://icanhazdadjoke.com/")
        var connection = jokeUrl.openConnection() as HttpURLConnection
        return BadJoke(connection).getJoke().joke
    }

    override fun onPreExecute() {
        super.onPreExecute()
        refreshButton.setEnabled(false)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        textBox.setText(result)
        refreshButton.setEnabled(true)
    }
}
