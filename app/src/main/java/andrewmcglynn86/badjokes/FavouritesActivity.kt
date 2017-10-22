package andrewmcglynn86.badjokes

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class FavouritesActivity : ListActivity() {

    lateinit var jokes: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var likes = GetLikedJokesTask(this, applicationContext)
        likes.execute()
    }

    fun setDisplayList(values: Array<String>) {
        jokes = values
        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        Toast.makeText(this, jokes.get(position), Toast.LENGTH_SHORT).show()
    }
}
