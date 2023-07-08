package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.NumberPicker

class LevelActivity : AppCompatActivity() {
    private lateinit var loBeginner: LinearLayout
    private lateinit var loInter: LinearLayout
    private lateinit var loAdv: LinearLayout
    private lateinit var btnLCont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loBeginner = findViewById(R.id.loBeginner)
        loBeginner.setOnClickListener{
            //
        }

        loInter = findViewById(R.id.loInter)
        loInter.setOnClickListener{
            //
        }

        loAdv = findViewById(R.id.loAdv)
        loAdv.setOnClickListener{
            //
        }

        btnLCont = findViewById(R.id.btnLCont)
        btnLCont.setOnClickListener{
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