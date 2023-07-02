package com.gofit.gofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutActivity : AppCompatActivity() {
    private lateinit var rvWorkouts: RecyclerView
    private lateinit var tvWLabel: TextView

    data class Item(val imgId: Int, val workoutName: String, val workoutInfo: String)

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

        rvWorkouts = findViewById(R.id.rvWorkouts)
        rvWorkouts.layoutManager = LinearLayoutManager(this)
        tvWLabel = findViewById(R.id.tvWLabel)
        tvWLabel.text = itemId.toString()

        val adapter = RVAdaptorWorkout(items)
        rvWorkouts.adapter = adapter


    }
}