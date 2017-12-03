package andrewmcglynn86.badjokes.db

import andrewmcglynn86.badjokes.dto.Joke
import android.content.ContentValues
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase



class JokeDb (var dbHelper: DBHelper){

    fun jokeExists(joke: Joke) : Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"),
                "online_joke_id like ?", arrayOf(joke.id), null, null, null);
        var exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun deleteJoke(joke: Joke) {
        val db = dbHelper.getWritableDatabase()
        db.delete("joke", "online_joke_id = ?", arrayOf(joke.id))
    }

    fun saveJoke(joke: Joke) {
        val db = dbHelper.getWritableDatabase()

        val values = ContentValues()
        values.put("joke_text", joke.joke)
        values.put("online_joke_id", joke.id)

        db.insert("joke", null, values)
    }

    fun getJokesCount() : Long {
        val db = dbHelper.readableDatabase
        val count = DatabaseUtils.queryNumEntries(db, "joke")
        db.close()
        return count
    }

    fun getJokes(limit: Int, offset: Int) : ArrayList<Joke> {
        val db = dbHelper.readableDatabase
        val savedJokes = ArrayList<Joke>()

        println("reading from db at offset ${offset} and limit ${limit}")

        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"), null,
                null, null, null, null, "${offset}, ${limit}")

        while (cursor.moveToNext()) {
            savedJokes.add(Joke(cursor.getString(1), cursor.getString(0), 200))
        }
        cursor.close()
        return savedJokes
    }

    fun getAllJokes() : ArrayList<Joke> {
        val db = dbHelper.readableDatabase
        val savedJokes = ArrayList<Joke>()

        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"), null, null, null, null, null)

        while (cursor.moveToNext()) {
            savedJokes.add(Joke(cursor.getString(1), cursor.getString(0), 200))
        }
        cursor.close()

        return savedJokes
    }
}
