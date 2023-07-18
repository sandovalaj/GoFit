package com.gofit.gofit

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditProfileActivity : AppCompatActivity() {
    private lateinit var etFName: EditText
    private lateinit var etLName: EditText
    private lateinit var etBirthday: TextView
    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rbOthers: RadioButton

    private lateinit var btnEditSave: Button

    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private var newTimestamp: Timestamp? = DataManager.birthday
    private var newGender: Int = DataManager.gender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etFName = findViewById(R.id.etFName)
        etLName = findViewById(R.id.etLName)
        etBirthday = findViewById(R.id.etBirthday)
        rgGender = findViewById(R.id.rgGender)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        rbOthers = findViewById(R.id.rbOthers)
        btnEditSave = findViewById(R.id.btnEditSave)

        etFName.setText(DataManager.fname)
        etLName.setText(DataManager.lname)

        val date = DataManager.birthday?.toDate()
        etBirthday.text = dateFormat.format(date)

        when (DataManager.gender) {
            1 -> rgGender.check(rbMale.id)
            2 -> rgGender.check(rbFemale.id)
            3 -> rgGender.check(rbOthers.id)
        }

        rgGender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbMale -> newGender = 1
                R.id.rbFemale -> newGender = 2
                R.id.rbOthers -> newGender = 3
            }
        }

        etBirthday.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(year, month, dayOfMonth)

                    newTimestamp = Timestamp(selectedDate.time)
                    etBirthday.text = dateFormat.format(newTimestamp!!.toDate())
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            datePickerDialog.show()
        }

        btnEditSave.setOnClickListener{
            DataManager.fname = etFName.text.toString()
            DataManager.lname = etLName.text.toString()
            DataManager.birthday = newTimestamp
            DataManager.gender = newGender

            var success = DataManager.updateDataManager()
            if (success) {
                Toast.makeText(this, "Profile updated.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Sorry, something went wrong.", Toast.LENGTH_SHORT).show()
            }
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