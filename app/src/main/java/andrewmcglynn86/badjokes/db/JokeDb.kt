package andrewmcglynn86.badjokes.db

import andrewmcglynn86.badjokes.dto.Joke

class JokeDb {

    fun jokeExists(dbHelper: DBHelper, jokeId: String) : Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"),
                "online_joke_id like ?", arrayOf(jokeId), null, null, null);
        var exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun getAllJokes(dbHelper: DBHelper) : ArrayList<Joke> {
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
