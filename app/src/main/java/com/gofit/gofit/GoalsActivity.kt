package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker

class GoalsActivity : AppCompatActivity() {
    private lateinit var btnWeightLoss: Button
    private lateinit var btnMuscle: Button
    private lateinit var btnOverall: Button
    private lateinit var btnGCont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnWeightLoss = findViewById(R.id.btnWeightLoss)
        btnWeightLoss.setOnClickListener{
            //
        }

        btnMuscle = findViewById(R.id.btnMuscle)
        btnMuscle.setOnClickListener{
            //
        }

        btnOverall = findViewById(R.id.btnOverall)
        btnOverall.setOnClickListener{
            //
        }

        btnGCont = findViewById(R.id.btnGCont)
        btnGCont.setOnClickListener{
            var intent = Intent(this, TargetActivity::class.java)
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