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
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    private lateinit var btnSignup: Button
    private lateinit var tvWDescr: TextView
    private lateinit var etFName: EditText
    private lateinit var etLName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    lateinit var FName: String
    lateinit var LName: String
    lateinit var Email: String
    lateinit var Password: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        auth = FirebaseAuth.getInstance()

        etFName = findViewById(R.id.etFName)
        etLName = findViewById(R.id.etLName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)

        btnSignup = findViewById(R.id.btnSignup)
        btnSignup.setOnClickListener{
            FName = etFName.text.toString().trim()
            LName = etLName.text.toString().trim()
            Email = etEmail.text.toString().trim()
            Password = etPassword.text.toString().trim()

            var editTextList = listOf(etFName, etLName, etEmail, etPassword)
            var inc = false

            for (editText in editTextList) {
                val text = editText.text.toString().trim()

                if (text.isEmpty()) {
                    editText.error = "Please fill in this field."
                    inc = true
                } else {
                    editText.error = null
                }
            }

            if (!inc) {
                auth.createUserWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign-up success, navigate to the next activity (e.g., MainActivity)
                            val intent = Intent(this, WelcomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Sign-up failed, show an error message
                            Toast.makeText(this, "Sign-up failed. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else
                return@setOnClickListener

        }

        tvWDescr = findViewById(R.id.tvWDesc)

        var text = "By joining GoFit, you agree to our Terms of Service"
        var ssText = SpannableString(text)
        var clickText = object : ClickableSpan() {
            override fun onClick(view: View) {
//                var intent = Intent(this@SignupActivity, LoginActivity::class.java)
//                startActivity(intent)
            }
        }

        ssText.setSpan(clickText, 35, 51, 0)
        tvWDescr.text = ssText
        tvWDescr.movementMethod = LinkMovementMethod.getInstance()
    }
}

