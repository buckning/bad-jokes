package andrewmcglynn86.badjokes.activities

import andrewmcglynn86.badjokes.tasks.GetLikedJokesTask
import andrewmcglynn86.badjokes.dto.Joke
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

class FavouritesActivity : ListActivity() {

    var jokes: ArrayList<Joke>? = null
    val MAX_JOKE_LENGTH = 127

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var likes = GetLikedJokesTask(this, applicationContext)
        likes.execute()
    }

    fun setDisplayList(values: ArrayList<Joke>?) {
        jokes = values

        var jokesDisplayed = ArrayList<String>()
        jokes?.forEach {
            var jokeText = it.joke
            jokeText.replace('\n', ' ')

            if(jokeText.length > MAX_JOKE_LENGTH) {
                jokeText = jokeText.substring(0, MAX_JOKE_LENGTH - 3) + "..."
            }
            jokesDisplayed.add(jokeText)
        }

        var array = jokesDisplayed.toTypedArray()

        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("joke", jokes?.get(position))
        startActivity(intent)
    }
}
