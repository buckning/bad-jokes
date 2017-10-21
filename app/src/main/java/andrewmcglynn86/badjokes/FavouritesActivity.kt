package andrewmcglynn86.badjokes

import android.app.ListActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class FavouritesActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var likes = GetLikedJokesTask(this, applicationContext)
        likes.execute()
    }

    fun setDisplayList(values: Array<String>) {
        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        listAdapter = arrayAdapter
    }
}
