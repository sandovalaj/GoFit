package com.gofit.gofit

import Workout
import android.annotation.SuppressLint
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignupSuccessActivity : AppCompatActivity() {
    private lateinit var btnSCont: Button
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupsuccess)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSCont = findViewById(R.id.btnSCont)
        btnSCont.setOnClickListener{
            DataManager.workouts = forYouWorkouts()
            DataManager.addDocument()

            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Range")
    fun forYouWorkouts(): MutableList<Workout> {
        var databaseHelper = DatabaseHelper(this)
        db = databaseHelper.openDatabase()

        var query = "SELECT * FROM Workouts w WHERE fitness_goal_id = ? AND physical_level_id = ?"
        var selectionArgs = arrayOf(DataManager.goal.toString(), DataManager.level.toString())
        var cursor = db.rawQuery(query, selectionArgs)

        var list : MutableList<Workout> = mutableListOf()

        while (cursor.moveToNext()) {
            val workout_id = cursor.getInt(cursor.getColumnIndex("workout_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val repetitions = cursor.getString(cursor.getColumnIndex("repetitions"))
            val duration = cursor.getInt(cursor.getColumnIndex("duration"))
            val met = cursor.getDouble(cursor.getColumnIndex("met"))

            val workout = Workout(workout_id, img, name, description, repetitions, duration, met)

            list.add(workout)
        }

        cursor.close()

        return list
    }
}