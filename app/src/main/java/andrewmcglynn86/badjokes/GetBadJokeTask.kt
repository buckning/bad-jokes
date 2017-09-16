package andrewmcglynn86.badjokes

import android.os.AsyncTask
import android.widget.Button
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


/**
 * Created by amcglynn on 15/09/2017.
 */
class GetBadJokeTask(var textBox: TextView, var refreshButton: Button) : AsyncTask<Void, Void, String>() {

    override fun doInBackground(vararg params: Void?): String? {
        var myUrl = URL("https://icanhazdadjoke.com/")
        var connection = myUrl.openConnection() as HttpURLConnection
        connection.setRequestProperty("Accept", "text/plain")
        connection.requestMethod = "GET"
        connection.connect()

        var inputStreamReader = InputStreamReader(connection.inputStream)
        //https://stackoverflow.com/questions/39500045/in-kotlin-how-do-i-read-the-entire-contents-of-an-inputstream-into-a-string
        var inputString = inputStreamReader.buffered().use {
            it.readText()
        }

        return inputString
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