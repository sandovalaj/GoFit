package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class GenderActivity : AppCompatActivity() {
    private lateinit var btnFemale: Button
    private lateinit var btnMale: Button
    private lateinit var btnNo: Button
    private lateinit var btnGCont: Button
    private lateinit var tvPrompt: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvPrompt = findViewById(R.id.tvPrompt)

        btnMale = findViewById(R.id.btnMale)
        btnMale.setOnClickListener{
            btnMale.isSelected = true
            btnFemale.isSelected = false
            btnNo.isSelected = false
        }

        btnFemale = findViewById(R.id.btnFemale)
        btnFemale.setOnClickListener{
            btnMale.isSelected = false
            btnFemale.isSelected = true
            btnNo.isSelected = false
        }

        btnNo = findViewById(R.id.btnNo)
        btnNo.setOnClickListener{
            btnMale.isSelected = false
            btnFemale.isSelected = false
            btnNo.isSelected = true
        }

        btnGCont = findViewById(R.id.btnGCont)
        btnGCont.setOnClickListener{
            if (!btnMale.isSelected && !btnFemale.isSelected && !btnNo.isSelected) {
                tvPrompt.text = "Please select an option."
                return@setOnClickListener
            }

            tvPrompt.text = ""
            var intent = Intent(this, BirthdayActivity::class.java)
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

