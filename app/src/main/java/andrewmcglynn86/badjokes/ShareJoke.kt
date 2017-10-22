package andrewmcglynn86.badjokes

import android.content.Intent

class ShareJoke(val joke: JokeResponse) {
    fun getShareIntent() : Intent? {
        if(joke.status != 200) {
            return null
        }

        val sendIntent = Intent()
        sendIntent.setAction(Intent.ACTION_SEND)
        sendIntent.putExtra(Intent.EXTRA_TEXT, joke.joke);
        sendIntent.setType("text/plain");
        return sendIntent
    }
}