package com.gofit.gofit

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FinishActivity : AppCompatActivity() {
    private lateinit var tvBMIValueF: TextView
    private lateinit var tvBMIResultF: TextView

    private lateinit var tvWorkoutsValueF: TextView
    private lateinit var tvCaloriesValueF: TextView
    private lateinit var tvMinutesValueF: TextView

    private lateinit var btnCloseF: Button

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

        btnCloseF.setOnClickListener {
            // save data of report

            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}