package com.gofit.gofit

import Workout
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ExerciseActivity : AppCompatActivity() {
    private lateinit var tvExerciseLabel: TextView
    private lateinit var ivWorkout: ImageView
    private lateinit var tvDesc: TextView
    private lateinit var imgBack: ImageView
    private lateinit var imgFront: ImageView
    private lateinit var tvPage: TextView
    private lateinit var btnClose: Button
    private lateinit var ivBookmark: ImageView

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
        ivBookmark = findViewById(R.id.ivBookmark)


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

            if (current == workoutListSize - 1) {
                btnClose.text = "FINISH"
            } else
                btnClose.text = "CLOSE"
        }

        imgFront.setOnClickListener{
            if (current < workoutListSize - 1) {
                current += 1
                show(workoutList?.get(current)!!)
            }

            imgBack.isEnabled = current > 0
            imgFront.isEnabled = current < workoutListSize - 1

            if (current == workoutListSize - 1) {
                btnClose.text = "FINISH"
            } else
                btnClose.text = "CLOSE"
        }

        ivBookmark.setOnClickListener{
            DataManager.favorites.add(workoutList?.get(current)!!)
            var success = DataManager.updateDataManager()
            if (success) {
                Toast.makeText(this, "Exercise added to favorites!.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show()
            }
        }

        btnClose.setOnClickListener{
            if (current == workoutListSize - 1) {
                var intent = Intent(this, FinishActivity::class.java)
                startActivityForResult(intent, 101)
            } else
                finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
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