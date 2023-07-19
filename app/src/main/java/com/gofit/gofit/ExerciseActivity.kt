package com.gofit.gofit

import Workout
import android.annotation.SuppressLint
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ExerciseActivity : AppCompatActivity() {
    private lateinit var tvExerciseLabel: TextView
    private lateinit var ivWorkout: ImageView
    private lateinit var tvDesc: TextView
    private lateinit var imgBack: ImageView
    private lateinit var imgFront: ImageView
    private lateinit var tvPage: TextView
    private lateinit var btnClose: Button

    private var workoutListSize: Int = 0
    private var current: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        current = intent.getIntExtra("workoutId", 0)

        var workoutList = intent.getParcelableArrayListExtra<Workout>("workoutList")
        workoutListSize = intent.getIntExtra("numberWorkouts", 0)

        tvExerciseLabel = findViewById(R.id.tvExerciseLabel)
        ivWorkout = findViewById(R.id.ivWorkout)
        tvDesc = findViewById(R.id.tvDesc)
        imgBack = findViewById(R.id.imgBack)
        imgFront = findViewById(R.id.imgFront)
        tvPage = findViewById(R.id.tvPage)
        btnClose = findViewById(R.id.btnClose)


        var workout = workoutList?.get(current)
        show(workout!!)

        imgBack.isEnabled = current > 0
        imgFront.isEnabled = current < workoutListSize - 1

        imgBack.setOnClickListener {
            if (current > 0) {
                current -= 1
                show(workoutList?.get(current)!!)
            }

            imgBack.isEnabled = current > 0
            imgFront.isEnabled = current < workoutListSize - 1
        }

        imgFront.setOnClickListener{
            if (current < workoutListSize - 1) {
                current += 1
                show(workoutList?.get(current)!!)
            }

            imgBack.isEnabled = current > 0
            imgFront.isEnabled = current < workoutListSize - 1
        }

        btnClose.setOnClickListener{
            finish()
        }
    }

    fun show(workout: Workout) {
        if (workout != null) {
            tvExerciseLabel.text = workout.name

            val resID = resources.getIdentifier(workout.img, "drawable", packageName)
            ivWorkout.setImageResource(resID)

            var cur = current + 1
            tvDesc.text = workout.description
            tvPage.text = "$cur / $workoutListSize"

        } else
            Log.e("Hatdog", "Workout null");
    }
}