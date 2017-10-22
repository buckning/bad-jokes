package andrewmcglynn86.badjokes

class JokeDb {
    val MAX_JOKE_LENGTH = 127

    fun jokeExists(dbHelper: DBHelper, jokeId: String) : Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"),
                "online_joke_id like ?", arrayOf(jokeId), null, null, null);
        var exists = cursor.moveToFirst()
        cursor.close()
        return exists
    }

    fun getAllJokes(dbHelper: DBHelper) : ArrayList<String> {
        val db = dbHelper.readableDatabase
        val savedJokes = ArrayList<String>()

        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"), null, null, null, null, null)

        while (cursor.moveToNext()) {
            var jokeText = cursor.getString(0)
            jokeText.replace('\n', ' ')
            if(jokeText.length > MAX_JOKE_LENGTH) {
                jokeText = jokeText.substring(0, MAX_JOKE_LENGTH - 3) + "..."
            }
            savedJokes.add(jokeText)
        }
        cursor.close()

        return savedJokes
    }
}
