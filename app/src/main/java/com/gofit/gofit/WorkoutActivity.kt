package com.gofit.gofit

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutActivity : AppCompatActivity() {
    private lateinit var rvWorkouts: RecyclerView
    private lateinit var tvWLabel: TextView
    private lateinit var db: SQLiteDatabase

    data class Item(val imgId: Int, val name: String, val reps: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        val itemId = intent.getIntExtra("itemId", 0)

        // List of Workout Groups
        val items = listOf(
            Item(R.drawable.imgbabs, "JUMPING JACKS", "0:20 seconds"),
            Item(R.drawable.imgbarm, "SQUATS", "10 reps"),
            Item(R.drawable.imgbabs, "BURPEES", "20 reps"),
            Item(R.drawable.imgbshouldersback, "PUSH UPS", "0:30 seconds"),
            Item(R.drawable.imgbleg, "PIKE COMPRESSIONS", "10 reps")
        )


        // Process the query results here (iterate through the cursor, etc.)


        rvWorkouts = findViewById(R.id.rvWorkouts)
        rvWorkouts.layoutManager = LinearLayoutManager(this)
        tvWLabel = findViewById(R.id.tvWLabel)
        tvWLabel.text = itemId.toString()

        val adapter = RVAdaptorWorkout(items)
        rvWorkouts.adapter = adapter


    }
}