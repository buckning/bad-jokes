package andrewmcglynn86.badjokes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by amcglynn on 19/09/2017.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        println("Not supported!")
        throw UnsupportedOperationException("DB upgrade not supported")
    }

    companion object {
        val DB_NAME = "jokes.db"
        val DB_VERSION = 1

        val CREATE_TABLE = "CREATE TABLE joke (joke_id INTEGER PRIMARY KEY, joke_text TEXT)"
    }
}