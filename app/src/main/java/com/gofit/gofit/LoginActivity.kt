package com.gofit.gofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var auth: FirebaseAuth

    lateinit var Email: String
    lateinit var Password: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener{
            Email = etEmail.text.toString().trim()
            Password = etPassword.text.toString().trim()

            var editTextList = listOf(etEmail, etPassword)
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
                auth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            DataManager.fetchUserData(this)
                        } else {
                            Toast.makeText(this, "Authentication failed. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else
                return@setOnClickListener
        }
    }

    fun onUserDataFetched() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onFetchError(e: Exception) {
        Log.e("Hatdog", "Cannot fetch data", e)
    }
}

