package andrewmcglynn86.badjokes

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton
import android.widget.CompoundButton



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

        val likeButton = findViewById(R.id.likeButton) as ToggleButton
        likeButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                var likeJokeTask = LikeJokeTask(likeButton, applicationContext, jokeManager.currentJoke)
                likeJokeTask.execute()
            } else {
                var unlikeJokeTask = UnlikeJokeTask(likeButton, applicationContext, jokeManager.currentJoke)
                unlikeJokeTask.execute()
            }
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
