package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class BirthdayActivity : AppCompatActivity() {
    private lateinit var pickerBday: DatePicker
    private lateinit var btnBCont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pickerBday = findViewById(R.id.pickerBday)
        val calendar = Calendar.getInstance()
        calendar.set(1970, Calendar.JANUARY, 1)
        val minDate = calendar.timeInMillis

        calendar.set(2023, Calendar.DECEMBER, 31)
        val maxDate = calendar.timeInMillis

        pickerBday.minDate = minDate
        pickerBday.maxDate = maxDate

        val year = pickerBday.year
        val month = pickerBday.month
        val day = pickerBday.dayOfMonth

        btnBCont = findViewById(R.id.btnBCont)
        btnBCont.setOnClickListener{
            var intent = Intent(this, HeightActivity::class.java)
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

