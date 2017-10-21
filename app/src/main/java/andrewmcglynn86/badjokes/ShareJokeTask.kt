package andrewmcglynn86.badjokes

import android.content.Context
import android.content.Intent
import android.os.AsyncTask

class ShareJokeTask (var context: Context, val joke: JokeResponse) : AsyncTask<Void, Void, Long>() {
    override fun doInBackground(vararg params: Void?): Long? {
        if(joke.status == 200) {
            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, joke.joke);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent)

            return 0
        }
        println("not sharing joke " + joke)
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
