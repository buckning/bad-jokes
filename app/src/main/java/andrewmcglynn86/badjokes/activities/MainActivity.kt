package andrewmcglynn86.badjokes.activities

import andrewmcglynn86.badjokes.*
import andrewmcglynn86.badjokes.androidops.ShareJoke
import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.tasks.GetBadJokeTask
import andrewmcglynn86.badjokes.tasks.IsLikedJokesTask
import andrewmcglynn86.badjokes.tasks.LikeJokeTask
import andrewmcglynn86.badjokes.tasks.UnlikeJokeTask
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        var initialJoke = readJokeFromIntent()

        var jokeManager = JokeManager()

        val likeButton = setUpLikeButtonBehaviour(jokeManager)

        var textField = findViewById(R.id.andrew) as TextView
        var refreshButton = findViewById(R.id.refreshButton) as Button
        refreshButton.setOnClickListener {
            var joke1 = GetBadJokeTask(this, jokeManager, textField, refreshButton, applicationContext)
            joke1.execute()
        }

        if(initialJoke == null) {
            textField.setText("Joke is loading...")
            var jokeTask = GetBadJokeTask(this, jokeManager, textField, refreshButton, applicationContext)
            val joke = jokeTask.execute().get()

            jokeManager.currentJoke = joke

            var jokeLikedTask = IsLikedJokesTask(joke.id, this)
            var jokeLiked = jokeLikedTask.execute().get()

            likeButton.isChecked = jokeLiked
            println("joke exists = " + jokeLiked)
        } else {
            textField.setText(initialJoke.joke)
            jokeManager.currentJoke = initialJoke

            var jokeLikedTask = IsLikedJokesTask(initialJoke.id, this)
            var jokeLiked = jokeLikedTask.execute().get()

            likeButton.isChecked = jokeLiked
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

    fun setUpLikeButtonBehaviour(jokeManager: JokeManager) : ToggleButton {
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
        return likeButton
    }

    fun readJokeFromIntent() : Joke? {
        return intent.getParcelableExtra("joke")
    }

    fun switchToFavourites() {
        val intent = Intent(this, FavouritesActivity::class.java)
        startActivity(intent)
    }
}
