package andrewmcglynn86.badjokes.androidops

import andrewmcglynn86.badjokes.dto.Joke
import android.content.Intent

class ShareJoke(val joke: Joke) {
    fun getShareIntent() : Intent? {
        if(joke.status != 200) {
            return null
        }

        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, joke.joke);
        intent.setType("text/plain");
        return intent
    }
}