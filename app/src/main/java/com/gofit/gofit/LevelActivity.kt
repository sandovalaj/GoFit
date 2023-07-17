package com.gofit.gofit

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView

class LevelActivity : AppCompatActivity() {
    private lateinit var loBeginner: LinearLayout
    private lateinit var loInter: LinearLayout
    private lateinit var loAdv: LinearLayout
    private lateinit var btnLCont: Button
    private lateinit var tvPrompt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val greyColor = "#D5D6D6"
        var selected = false

        loBeginner = findViewById(R.id.loBeginner)
        loBeginner.setOnClickListener{
            loBeginner.setBackgroundColor(Color.RED)
            loInter.setBackgroundColor(Color.parseColor(greyColor))
            loAdv.setBackgroundColor(Color.parseColor(greyColor))
            DataManager.level = 1
            selected = true
        }

        loInter = findViewById(R.id.loInter)
        loInter.setOnClickListener{
            loBeginner.setBackgroundColor(Color.parseColor(greyColor))
            loInter.setBackgroundColor(Color.RED)
            loAdv.setBackgroundColor(Color.parseColor(greyColor))
            DataManager.level = 2
            selected = true
        }

        loAdv = findViewById(R.id.loAdv)
        loAdv.setOnClickListener{
            loBeginner.setBackgroundColor(Color.parseColor(greyColor))
            loInter.setBackgroundColor(Color.parseColor(greyColor))
            loAdv.setBackgroundColor(Color.RED)
            DataManager.level = 3
            selected = true
        }

        tvPrompt = findViewById(R.id.tvPrompt)
        btnLCont = findViewById(R.id.btnLCont)
        btnLCont.setOnClickListener{
            if (!selected) {
                tvPrompt.text = "Please select an option."
                return@setOnClickListener
            }

            tvPrompt.text = ""
            var intent = Intent(this, SignupSuccessActivity::class.java)
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