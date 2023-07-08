package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.NumberPicker

class TargetActivity : AppCompatActivity() {
    private lateinit var btnAbs: Button
    private lateinit var btnArms: Button
    private lateinit var btnChest: Button
    private lateinit var btnSnB: Button
    private lateinit var btnLegs: Button
    private lateinit var btnWhole: Button
    private lateinit var btnTCont: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnAbs = findViewById(R.id.btnAbs)
        btnAbs.setOnClickListener{
            //
        }

        btnArms = findViewById(R.id.btnArms)
        btnArms.setOnClickListener{
            //
        }

        btnChest = findViewById(R.id.btnChest)
        btnChest.setOnClickListener{
            //
        }

        btnSnB = findViewById(R.id.btnSnB)
        btnSnB.setOnClickListener{
            //
        }

        btnLegs = findViewById(R.id.btnLegs)
        btnLegs.setOnClickListener{
            //
        }

        btnWhole = findViewById(R.id.btnWhole)
        btnWhole.setOnClickListener{
            //
        }

        btnTCont = findViewById(R.id.btnTCont)
        btnTCont.setOnClickListener{
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