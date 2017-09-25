package andrewmcglynn86.badjokes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class FavouritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        var layout = findViewById(R.id.favouritesLinearLayout) as LinearLayout
        var likes = GetLikedJokesTask(applicationContext, layout)
        likes.execute()
    }
}
