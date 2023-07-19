package com.gofit.gofit

import Workout
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WorkoutActivity : AppCompatActivity() {
    private lateinit var rvWorkouts: RecyclerView
    private lateinit var tvWLabel: TextView
    private lateinit var tvWInfo: TextView

    private var label: String = ""
    private var items : MutableList<Item> = mutableListOf()
    private var totalDuration: Int = 0
    private var numberWorkouts: Int = 0

    data class Item(val imgId: Int, val name: String, val reps: String, val workout: Workout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        rvWorkouts = findViewById(R.id.rvWorkouts)
        tvWLabel = findViewById(R.id.tvWLabel)
        tvWInfo = findViewById(R.id.tvWInfo)

        label = intent.getStringExtra("name").toString()

        var workoutList = intent.getParcelableArrayListExtra<Workout>("workoutList")

        tvWLabel.text = label

        if (workoutList != null) {
            for (workout in workoutList) {
                var drawable: Drawable? = null
                var name = ""
                var reps = ""
                val w: Workout

                val resID = resources.getIdentifier(workout.img, "drawable", packageName)

                name = workout.name
                reps = workout.repetitions
                w = workout

                items.add(Item(resID, name, reps, w))

                numberWorkouts += 1
                totalDuration += workout.duration
            }
        }

        var temp = totalDuration.toDouble() / 60
        temp = Math.ceil(temp)

        val t = "$numberWorkouts workouts | $temp minutes"
        tvWInfo.text = t

        val adapter = RVAdaptorWorkout(items, numberWorkouts, workoutList as ArrayList<Workout>)
        rvWorkouts.layoutManager = LinearLayoutManager(this)
        rvWorkouts.adapter = adapter
    }
}