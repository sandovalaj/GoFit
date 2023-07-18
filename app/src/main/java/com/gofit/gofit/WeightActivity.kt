package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker

class WeightActivity : AppCompatActivity() {
    private lateinit var pickerWeight: NumberPicker
    private lateinit var btnWCont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pickerWeight = findViewById(R.id.pickerWeight)
        pickerWeight.minValue = 0
        pickerWeight.maxValue = 500

        btnWCont = findViewById(R.id.btnWCont)
        btnWCont.setOnClickListener{
            DataManager.weight = pickerWeight.value

            var intent = Intent(this, GoalsActivity::class.java)
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