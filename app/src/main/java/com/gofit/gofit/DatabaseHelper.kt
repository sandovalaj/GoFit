package com.gofit.gofit

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "workout_database.db"
        private const val DATABASE_VERSION = 1
    }

    private var database: SQLiteDatabase? = null

    init {
        try {
            createDatabase()
        } catch (e: IOException) {
            // Handle the exception, e.g., display an error message
            Log.e("Hatdog", "Error in creating database.");
        }
    }

    private fun createDatabase() {
        val dbPath = context.getDatabasePath(DATABASE_NAME).path

        if (!checkDatabaseExists(dbPath)) {
            try {
                val checkDB = context.assets.open(DATABASE_NAME)
                val buffer = ByteArray(checkDB.available())
                checkDB.read(buffer)
                checkDB.close()

                val output = FileOutputStream(dbPath)
                output.write(buffer)
                output.flush()
                output.close()
            } catch (e: IOException) {
                Log.e("Hatdog", "Error in creating database 2.");
            }
        }
    }

    private fun checkDatabaseExists(dbPath: String): Boolean {
        val dbFile = context.getDatabasePath(dbPath)
        return dbFile.exists()
    }

    @Synchronized
    fun openDatabase(): SQLiteDatabase {
        if (database == null || !database!!.isOpen) {
            val dbPath = context.getDatabasePath(DATABASE_NAME).path
            database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
        }
        return database!!
    }

    override fun onCreate(db: SQLiteDatabase) {
        // If you need to perform any specific operations during database creation,
        // you can do them here.
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // If you need to handle database upgrades, you can do it here.
    }

    override fun close() {
        database?.close()
        super.close()
    }
}