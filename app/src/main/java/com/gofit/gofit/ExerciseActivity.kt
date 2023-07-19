package com.gofit.gofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ExerciseActivity : AppCompatActivity() {
    private lateinit var tvLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        tvLabel = findViewById(R.id.tvExerciseLabel)
        val itemId = intent.getIntExtra("itemId", 0)
        tvLabel.text = itemId.toString()
    }
}