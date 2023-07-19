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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        var clickedWorkout: Int = intent.getIntExtra("workoutId", 0)

        var workoutList = intent.getParcelableArrayListExtra<Workout>("workoutList")

        tvExerciseLabel = findViewById(R.id.tvExerciseLabel)
        ivWorkout = findViewById(R.id.ivWorkout)
        tvDesc = findViewById(R.id.tvDesc)
        imgBack = findViewById(R.id.imgBack)
        imgFront = findViewById(R.id.imgFront)
        tvPage = findViewById(R.id.tvPage)
        btnClose = findViewById(R.id.btnClose)


        var workout = workoutList?.get(clickedWorkout)

        if (workout != null) {
            tvExerciseLabel.text = workout.name

            val resID = resources.getIdentifier(workout.img, "drawable", packageName)
            ivWorkout.setImageResource(resID)

            tvDesc.text = workout.description
        } else
            Log.e("Hatdog", "Workout null");


        tvPage.text = intent.getIntExtra("numberWorkouts", 0).toString()

        imgBack.setOnClickListener {

        }

        imgFront.setOnClickListener{

        }

        btnClose.setOnClickListener{
            finish()
        }

    }
}