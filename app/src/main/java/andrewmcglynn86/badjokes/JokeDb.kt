package andrewmcglynn86.badjokes

/**
 * Created by amcglynn on 22/10/2017.
 */
class JokeDb {

    fun jokeExists(dbHelper: DBHelper, jokeId: String) : Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
                "joke", arrayOf("joke_text", "online_joke_id"),
                "online_joke_id like ?", arrayOf(jokeId), null, null, null);

        return cursor.moveToFirst()
    }
}
