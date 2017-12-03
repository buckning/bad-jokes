package andrewmcglynn86.badjokes.activities

import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.db.DBHelper
import andrewmcglynn86.badjokes.db.JokeDb
import andrewmcglynn86.badjokes.tasks.GetLikedJokesTask
import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.AbsListView



class FavouritesActivity : ListActivity() {

    lateinit var jokes: ArrayList<Joke>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dbHelper = DBHelper(this)
        val jokeDb = JokeDb(dbHelper)
        val jokeService = JokeService(OnlineJokeRepository("https://icanhazdadjoke.com/"), jokeDb)

        var likes = GetLikedJokesTask(jokeService)
        jokes = likes.execute().get()

        setDisplayList(jokeService, jokes)

        listView.setOnScrollListener(object: AbsListView.OnScrollListener {
            override fun onScroll(view: AbsListView, firstVisibleItem: Int,
                                  visibleItemCount: Int, totalItemCount: Int) {
                if (isAtEndOfList()) {
                    updateDisplayList(jokeService)
                }
            }

            override fun onScrollStateChanged(absListView: AbsListView, i: Int) { }

            fun isAtEndOfList() : Boolean {
                return (listView.getLastVisiblePosition() == listView.getAdapter().getCount() - 1
                        && listView.getChildAt(listView.getChildCount() - 1)
                        .getBottom() <= listView.getHeight())
            }
        })
    }

    fun updateDisplayList(jokeService: JokeService) {
        println("updating list...")
        jokeService.updateLikedJokes()
    }

    fun setDisplayList(jokeService: JokeService, values: ArrayList<Joke>) {
        var jokesToDisplay = jokeService.getAllLikeJokesTrimmed(values)

        var array = jokesToDisplay.toTypedArray()

        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("joke", jokes.get(position))
        startActivity(intent)
    }

    internal class ScrollListenerTest(var list: ListView) : AbsListView.OnScrollListener {
        override fun onScroll(view: AbsListView, firstVisibleItem: Int,
                              visibleItemCount: Int, totalItemCount: Int) {
            if (list.getLastVisiblePosition() == list.getAdapter().getCount() - 1
                    && list.getChildAt(list.getChildCount() - 1).getBottom() <= list.getHeight()) {
                println("end")
            }
        }

        override fun onScrollStateChanged(absListView: AbsListView, i: Int) {
            //no-op
        }
    }
}
