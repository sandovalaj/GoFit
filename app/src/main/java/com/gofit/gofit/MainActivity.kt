package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var btnSignup: Button
    private lateinit var tvLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSignup = findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener{
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        tvLogin = findViewById(R.id.tvWDescri)

        var text = "Already a Member? Log In"
        var ssText = SpannableString(text)
        var clickText = object : ClickableSpan() {
            override fun onClick(view: View) {
                var intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        ssText.setSpan(clickText, 18, 24, 0)
        tvLogin.text = ssText
        tvLogin.movementMethod = LinkMovementMethod.getInstance()
    }


}

