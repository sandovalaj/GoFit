package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button

class SignupActivity : AppCompatActivity() {
    private lateinit var btnSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSignup = findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener{
            var Intent = Intent(this, WelcomeActivity::class.java)
            startActivity(Intent)
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

