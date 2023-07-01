package com.gofit.gofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutActivity : AppCompatActivity() {
    private lateinit var rvWorkouts: RecyclerView
    data class Item(val imgId: Int, val workoutName: String, val workoutInfo: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        // List of Workout Groups
        val items = listOf(
            WorkoutActivity.Item(R.drawable.imgbabs, "JUMPING JACKS", "0:20 seconds"),
            WorkoutActivity.Item(R.drawable.imgbarm, "SQUATS", "10 reps"),
            WorkoutActivity.Item(R.drawable.imgbabs, "BURPEES", "20 reps"),
            WorkoutActivity.Item(R.drawable.imgbshouldersback, "PUSH UPS", "0:30 seconds"),
            WorkoutActivity.Item(R.drawable.imgbleg, "PIKE COMPRESSIONS", "10 reps")
        )

        rvWorkouts = findViewById(R.id.rvWorkouts)
        rvWorkouts.layoutManager = LinearLayoutManager(this)

        val adapter = RVAdaptorWorkout(items)
        rvWorkouts.adapter = adapter

    }
}