package andrewmcglynn86.badjokes.activities

import andrewmcglynn86.badjokes.*
import andrewmcglynn86.badjokes.androidops.ShareJoke
import andrewmcglynn86.badjokes.connection.OnlineJokeRepository
import andrewmcglynn86.badjokes.db.DBHelper
import andrewmcglynn86.badjokes.db.JokeDb
import andrewmcglynn86.badjokes.dto.Joke
import andrewmcglynn86.badjokes.service.JokeService
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

        val initialJoke = readJokeFromIntent()
        val jokeManager = JokeManager()

        val dbHelper = DBHelper(this)
        val jokeDb = JokeDb(dbHelper)
        val jokeService = JokeService(OnlineJokeRepository("https://icanhazdadjoke.com/"), jokeDb)

        val textField = findViewById(R.id.andrew) as TextView
        val refreshButton = findViewById(R.id.refreshButton) as Button
        val likeButton = findViewById(R.id.likeButton) as ToggleButton
        val shareButton = findViewById(R.id.shareButton) as Button
        var favouritesButton = findViewById(R.id.favouritesButton) as Button

        if(initialJoke == null) {
            textField.setText("Joke is loading...")

            loadNewJoke(jokeService, refreshButton, textField, likeButton, jokeManager)
        } else {
            textField.setText(initialJoke.joke)
            jokeManager.currentJoke = initialJoke

            updateLikeButton(jokeService, initialJoke, likeButton)
        }

        setUpLikeButtonBehaviour(likeButton, jokeService, jokeManager)

        refreshButton.setOnClickListener {
            loadNewJoke(jokeService, refreshButton, textField, likeButton, jokeManager)
        }

        shareButton.setOnClickListener {
            startActivity(ShareJoke(jokeManager.currentJoke).getShareIntent())
        }

        favouritesButton.setOnClickListener {
            switchToFavourites()
        }
    }

    fun updateLikeButton(jokeService: JokeService, joke: Joke, likeButton: ToggleButton) {
        likeButton.isChecked = IsLikedJokesTask(jokeService, joke).execute().get()
    }

    fun loadNewJoke(jokeService: JokeService, refreshButton: Button, textField: TextView,
                    likeButton: ToggleButton, jokeManager: JokeManager) {
        refreshButton.setEnabled(false)
        var result = GetBadJokeTask(jokeService).execute().get()
        textField.setText(result.joke)
        refreshButton.setEnabled(true)
        updateLikeButton(jokeService, result, likeButton)
        jokeManager.currentJoke = result
    }

    fun setUpLikeButtonBehaviour(likeButton: ToggleButton, jokeService: JokeService,
                                 jokeManager: JokeManager) {
        likeButton.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                var likeJokeTask = LikeJokeTask(jokeService, jokeManager.currentJoke)
                likeJokeTask.execute()
            } else {
                var unlikeJokeTask = UnlikeJokeTask(jokeService, jokeManager.currentJoke)
                unlikeJokeTask.execute()
            }
        }
    }

    fun readJokeFromIntent() : Joke? {
        return intent.getParcelableExtra("joke")
    }

    fun switchToFavourites() {
        val intent = Intent(this, FavouritesActivity::class.java)
        startActivity(intent)
    }
}
