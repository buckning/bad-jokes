package andrewmcglynn86.badjokes

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textField = findViewById(R.id.andrew) as TextView
        var refreshButton = findViewById(R.id.refreshButton) as Button

        textField.setText("Joke is loading...")
        var joke = GetBadJokeTask(this, textField, refreshButton)
        joke.execute()


        refreshButton.setOnClickListener{
            var joke1 = GetBadJokeTask(this, textField, refreshButton)
            joke1.execute()
        }
    }
}
