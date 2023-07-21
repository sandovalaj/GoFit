package com.gofit.gofit

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class FinishActivity : AppCompatActivity() {
    private lateinit var tvBMIValueF: TextView
    private lateinit var tvBMIResultF: TextView

    private lateinit var tvWorkoutsValueF: TextView
    private lateinit var tvCaloriesValueF: TextView
    private lateinit var tvMinutesValueF: TextView

    private lateinit var btnCloseF: Button

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        tvBMIValueF = findViewById(R.id.tvBMIValueF)
        tvBMIResultF = findViewById(R.id.tvBMIResultF)

        tvWorkoutsValueF = findViewById(R.id.tvWorkoutsValueF)
        tvCaloriesValueF = findViewById(R.id.tvCaloriesValueF)
        tvMinutesValueF = findViewById(R.id.tvMinutesValueF)

        btnCloseF = findViewById(R.id.btnCloseF)

        // Setting up BMI
        var bmi = (DataManager.weight.toDouble() / (DataManager.height.toDouble() * DataManager.height.toDouble())) * 703
        val bmiResult = when {
            bmi < 18.5 -> "Underweight"
            bmi < 24.9 -> "Healthy Weight"
            bmi < 29.9 -> "Overweight"
            else -> "Obese"
        }

        tvBMIValueF.text = String.format("%.2f", bmi)
        tvBMIResultF.text = bmiResult

        var numberWorkouts = intent.getIntExtra("numberWorkouts", 0)
        var calories = intent.getIntExtra("calories", 0)
        var minutes = intent.getIntExtra("minutes", 0)

        tvWorkoutsValueF.text = numberWorkouts.toString()
        tvCaloriesValueF.text = calories.toString()
        tvMinutesValueF.text = minutes.toString()

        btnCloseF.setOnClickListener {
            // save data of report

            DataManager.workoutsAccomplished += numberWorkouts
            DataManager.calories += calories
            DataManager.minutes += minutes

            var currentDate = Timestamp(Date())
            DataManager.workoutDates.add(currentDate)

            val uniqueDateStrings: HashSet<String> = HashSet()
            val uniqueTimestamps: MutableList<Timestamp> = mutableListOf()

            for (timestamp in DataManager.workoutDates) {
                val date = timestamp.toDate().toString().substringBefore("T") // Extract date part as string
                if (uniqueDateStrings.add(date)) {
                    uniqueTimestamps.add(timestamp)
                }
            }

            val uniqueDates: List<Timestamp> = DataManager.workoutDates.distinctBy { it.getDate() }

            DataManager.workoutDates = uniqueDates.toMutableList()
            DataManager.updateDataManager()

            var temp : MutableList<Date> = mutableListOf()
            for (i in DataManager.workoutDates) {
                temp.add(i.toDate())
            }

            Log.e("FinishActivity", "Dates: $temp");

            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun Timestamp.getDate(): LocalDate {
        // Convert the Timestamp to a LocalDateTime
        val localDateTime = this.toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        // Extract the date part
        return localDateTime.toLocalDate()
    }
}