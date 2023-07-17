package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker

class HeightActivity : AppCompatActivity() {
    private lateinit var pickerFeet: NumberPicker
    private lateinit var pickerInches: NumberPicker
    private lateinit var btnHCont: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pickerFeet = findViewById(R.id.pickerFeet)
        pickerInches = findViewById(R.id.pickerInches)

        pickerFeet.minValue = 0
        pickerFeet.maxValue = 10

        pickerInches.minValue = 0
        pickerInches.maxValue = 11

        var height = (pickerFeet.value * 12) + pickerInches.value

        btnHCont = findViewById(R.id.btnHCont)
        btnHCont.setOnClickListener{
            DataManager.height = height

            var intent = Intent(this, WeightActivity::class.java)
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