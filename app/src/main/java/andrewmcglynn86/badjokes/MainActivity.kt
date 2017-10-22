package andrewmcglynn86.badjokes

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var initialJoke = getIntent().getStringExtra("joke")

        var jokeManager = JokeManager()

        setContentView(R.layout.activity_main)

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

        var textField = findViewById(R.id.andrew) as TextView
        var refreshButton = findViewById(R.id.refreshButton) as Button
        refreshButton.setOnClickListener {
            var joke1 = GetBadJokeTask(this, jokeManager, textField, refreshButton, likeButton)
            joke1.execute()
        }

        if(initialJoke == null) {
            textField.setText("Joke is loading...")
            var joke = GetBadJokeTask(this, jokeManager, textField, refreshButton, likeButton)
            joke.execute()
        } else {
            textField.setText(initialJoke)
            jokeManager.currentJoke = JokeResponse("0000000", initialJoke, 200)
        }

        val shareButton = findViewById(R.id.shareButton) as Button
        shareButton.setOnClickListener {
            startActivity(ShareJoke(jokeManager.currentJoke).getShareIntent())
        }

        var favouritesButton = findViewById(R.id.favouritesButton) as Button
        favouritesButton.setOnClickListener {
            switchToFavourites()
        }
    }

    fun switchToFavourites() {
        val intent = Intent(this, FavouritesActivity::class.java)
        startActivity(intent)
    }
}
