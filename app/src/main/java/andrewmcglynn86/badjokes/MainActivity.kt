package andrewmcglynn86.badjokes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var jokeManager = JokeManager()

        setContentView(R.layout.activity_main)
        var textField = findViewById(R.id.andrew) as TextView
        var refreshButton = findViewById(R.id.refreshButton) as Button

        textField.setText("Joke is loading...")
        var joke = GetBadJokeTask(this, jokeManager, textField, refreshButton)
        joke.execute()

        var likeButton = findViewById(R.id.likeButton) as Button
        likeButton.setOnClickListener {
            var likeJokeTask = LikeJokeTask(likeButton, applicationContext, jokeManager.currentJoke)
            likeJokeTask.execute()
            //TODO
            //if the id is not in the DB, save the id to the DB
            //if it is in the DB, remove it from the db
        }

        refreshButton.setOnClickListener {
            var joke1 = GetBadJokeTask(this, jokeManager, textField, refreshButton)
            joke1.execute()
            likeButton.setBackgroundColor(Color.GRAY)
        }

        var favouritesButton = findViewById(R.id.favouritesButton) as Button
        favouritesButton.setOnClickListener {
            println("switching to favourites")
            switchToFavourites()
        }

    }

    fun switchToFavourites() {
        val intent = Intent(this, FavouritesActivity::class.java)
        startActivity(intent)
    }
}
