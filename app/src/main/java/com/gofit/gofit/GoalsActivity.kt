package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView

class GoalsActivity : AppCompatActivity() {
    private lateinit var btnWeightLoss: Button
    private lateinit var btnMuscle: Button
    private lateinit var btnOverall: Button
    private lateinit var btnGCont: Button
    private lateinit var tvGoalPrompt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnWeightLoss = findViewById(R.id.btnWeightLoss)
        btnWeightLoss.setOnClickListener{
            btnWeightLoss.setBackgroundResource(R.drawable.edittext_redfill_blackborder)
            btnMuscle.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnOverall.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnWeightLoss.isSelected = true
            btnMuscle.isSelected = false
            btnOverall.isSelected = false
            DataManager.goal = 1
        }

        btnMuscle = findViewById(R.id.btnMuscle)
        btnMuscle.setOnClickListener{
            btnWeightLoss.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnMuscle.setBackgroundResource(R.drawable.edittext_redfill_blackborder)
            btnOverall.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnWeightLoss.isSelected = false
            btnMuscle.isSelected = true
            btnOverall.isSelected = false
            DataManager.goal = 2
        }

        btnOverall = findViewById(R.id.btnOverall)
        btnOverall.setOnClickListener{
            btnWeightLoss.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnMuscle.setBackgroundResource(R.drawable.edittext_greyfill_blackborder)
            btnOverall.setBackgroundResource(R.drawable.edittext_redfill_blackborder)
            btnWeightLoss.isSelected = false
            btnMuscle.isSelected = false
            btnOverall.isSelected = true
            DataManager.goal = 3
        }

        tvGoalPrompt = findViewById(R.id.tvGoalPrompt)

        btnGCont = findViewById(R.id.btnGCont)
        btnGCont.setOnClickListener{
            if (!btnWeightLoss.isSelected && !btnMuscle.isSelected && !btnOverall.isSelected) {
                tvGoalPrompt.text = "Please select an option."
                return@setOnClickListener
            }

            tvGoalPrompt.text = ""
            var intent = Intent(this, LevelActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}