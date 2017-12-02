package andrewmcglynn86.badjokes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        println("Not supported!")
        throw UnsupportedOperationException("DB upgrade not supported")
    }

    companion object {
        val DB_NAME = "jokes1.db"
        val DB_VERSION = 1

        val CREATE_TABLE = "CREATE TABLE joke " +
                "(joke_id INTEGER PRIMARY KEY, online_joke_id TEXT, joke_text TEXT)"
    }
}