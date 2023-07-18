package com.gofit.gofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup

class EditProfileActivity : AppCompatActivity() {
    private lateinit var etFName: EditText
    private lateinit var etLName: EditText
    private lateinit var etBirthday: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var btnEditSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        etFName = findViewById(R.id.etFName)
        etLName = findViewById(R.id.etLName)
        etBirthday = findViewById(R.id.etBirthday)

        etFName.setText(DataManager.fname)
        etLName.setText(DataManager.lname)
        etBirthday.setText(DataManager.birthday)

    }
}