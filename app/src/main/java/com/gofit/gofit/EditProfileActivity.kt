package com.gofit.gofit

import Workout
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.applandeo.materialcalendarview.CalendarView
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class EditProfileActivity : AppCompatActivity() {
    private lateinit var etFName: EditText
    private lateinit var etMName: EditText
    private lateinit var etLName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etBirthday: TextView

    private lateinit var rgGender: RadioGroup
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var rbOthers: RadioButton

    private lateinit var rgCivil: RadioGroup
    private lateinit var rbSingle: RadioButton
    private lateinit var rbMarried: RadioButton
    private lateinit var rbDivorced: RadioButton

    private lateinit var etHeightValueFeet: TextView
    private lateinit var etHeightValueInches: TextView
    private lateinit var etWeightValue: TextView

    private lateinit var rgFitnessGoal: RadioGroup
    private lateinit var rbWeightLoss: RadioButton
    private lateinit var rbMuscleGain: RadioButton
    private lateinit var rbOverall: RadioButton

    private lateinit var rgFitnessLevel: RadioGroup
    private lateinit var rbBeginner: RadioButton
    private lateinit var rbInter: RadioButton
    private lateinit var rbAdvanced: RadioButton

    private lateinit var btnEditSave: Button

    private val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    private var newTimestamp: Timestamp? = DataManager.birthday
    private var newGender: Int = DataManager.gender
    private var newCivil: Int = DataManager.civil
    private var newGoal: Int = DataManager.goal
    private var newLevel: Int = DataManager.level
    private var newHeight: Int = DataManager.height
    private var newWeight: Int = DataManager.weight


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        etFName = findViewById(R.id.etFName)
        etMName = findViewById(R.id.etMName)
        etLName = findViewById(R.id.etLName)
        etBirthday = findViewById(R.id.etBirthday)
        etAddress = findViewById(R.id.etAddress)

        rgGender = findViewById(R.id.rgGender)
        rbMale = findViewById(R.id.rbMale)
        rbFemale = findViewById(R.id.rbFemale)
        rbOthers = findViewById(R.id.rbOthers)

        rgCivil = findViewById(R.id.rgCivil)
        rbSingle = findViewById(R.id.rbSingle)
        rbMarried = findViewById(R.id.rbMarried)
        rbDivorced = findViewById(R.id.rbDivorced)

        etHeightValueFeet = findViewById(R.id.etHeightValueFeet)
        etHeightValueInches = findViewById(R.id.etHeightValueInches)
        etWeightValue = findViewById(R.id.etWeightValue)

        rgFitnessGoal = findViewById(R.id.rgFitnessGoal)
        rbWeightLoss = findViewById(R.id.rbWeightLoss)
        rbMuscleGain = findViewById(R.id.rbMuscleGain)
        rbOverall = findViewById(R.id.rbOverall)

        rgFitnessLevel = findViewById(R.id.rgFitnessLevel)
        rbBeginner = findViewById(R.id.rbBeginner)
        rbInter = findViewById(R.id.rbInter)
        rbAdvanced = findViewById(R.id.rbAdvanced)

        btnEditSave = findViewById(R.id.btnEditSave)

//        Setting up data

        etFName.setText(DataManager.fname)
        etMName.setText(DataManager.mname)
        etLName.setText(DataManager.lname)
        etAddress.setText(DataManager.address)

        val date = DataManager.birthday?.toDate()
        etBirthday.text = dateFormat.format(date)

        when (DataManager.gender) {
            1 -> rgGender.check(rbMale.id)
            2 -> rgGender.check(rbFemale.id)
            3 -> rgGender.check(rbOthers.id)
        }

        when (DataManager.civil) {
            1 -> rgCivil.check(rbSingle.id)
            2 -> rgCivil.check(rbMarried.id)
            3 -> rgCivil.check(rbDivorced.id)
        }

        etHeightValueFeet.text = (DataManager.height / 12).toString()
        etHeightValueInches.text = (DataManager.height % 12).toString()
        etWeightValue.text = DataManager.weight.toString()

        when (DataManager.goal) {
            1 -> rgFitnessGoal.check(rbWeightLoss.id)
            2 -> rgFitnessGoal.check(rbMuscleGain.id)
            3 -> rgFitnessGoal.check(rbOverall.id)
        }

        when (DataManager.level) {
            1 -> rgFitnessLevel.check(rbBeginner.id)
            2 -> rgFitnessLevel.check(rbInter.id)
            3 -> rgFitnessLevel.check(rbAdvanced.id)
        }

//        Checking for changes

        rgGender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbMale -> newGender = 1
                R.id.rbFemale -> newGender = 2
                R.id.rbOthers -> newGender = 3
            }
        }

        rgCivil.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbSingle -> newCivil = 1
                R.id.rbMarried -> newCivil = 2
                R.id.rbDivorced -> newCivil = 3
            }
        }

        rgFitnessGoal.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbWeightLoss -> newGoal = 1
                R.id.rbMuscleGain -> newGoal = 2
                R.id.rbOverall -> newGoal = 3
            }
        }

        rgFitnessLevel.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbBeginner -> newLevel = 1
                R.id.rbInter -> newLevel = 2
                R.id.rbAdvanced -> newLevel = 3
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

        etWeightValue.setOnClickListener {
            val numberPicker = NumberPicker(this)
            numberPicker.minValue = 0
            numberPicker.maxValue = 500
            numberPicker.value = newWeight

            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("Select your weight in lbs")
                .setView(numberPicker)
                .setPositiveButton("OK") { _, _ ->
                    newWeight = numberPicker.value
                    etWeightValue.setText(newWeight.toString())
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }

        etHeightValueFeet.setOnClickListener {
            val numberPickerDialogView = layoutInflater.inflate(R.layout.dialogue_dual_number_picker, null)
            val feet = numberPickerDialogView.findViewById<NumberPicker>(R.id.npFeet)
            val inches = numberPickerDialogView.findViewById<NumberPicker>(R.id.npInches)

            feet.minValue = 1
            feet.maxValue = 10
            feet.value = newHeight / 12

            inches.minValue = 0
            inches.maxValue = 11
            inches.value = newHeight % 12

            val alertDialogBuilder = AlertDialog.Builder(this)
                .setTitle("Select Two Numbers")
                .setView(numberPickerDialogView)
                .setPositiveButton("OK") { _, _ ->
                    val f = feet.value
                    val i = inches.value

                    newHeight = (f * 12) + i
                    etHeightValueFeet.text = f.toString()
                    etHeightValueInches.text = i.toString()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


        btnEditSave.setOnClickListener{
            DataManager.fname = etFName.text.toString()
            DataManager.mname = etMName.text.toString()
            DataManager.lname = etLName.text.toString()
            DataManager.address = etAddress.text.toString()

            DataManager.civil = newCivil
            DataManager.height = newHeight
            DataManager.weight = newWeight
            DataManager.birthday = newTimestamp
            DataManager.gender = newGender
            DataManager.goal = newGoal
            DataManager.level = newLevel
            DataManager.workouts = forYouWorkouts()

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

    @SuppressLint("Range")
    fun forYouWorkouts(): MutableList<Workout> {
        var db: SQLiteDatabase
        var databaseHelper = DatabaseHelper(this)
        db = databaseHelper.openDatabase()

        var query = "SELECT * FROM Workouts w WHERE fitness_goal_id = ? AND physical_level_id = ?"
        var selectionArgs = arrayOf(DataManager.goal.toString(), DataManager.level.toString())
        var cursor = db.rawQuery(query, selectionArgs)

        var list : MutableList<Workout> = mutableListOf()

        while (cursor.moveToNext()) {
            val workout_id = cursor.getInt(cursor.getColumnIndex("workout_id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val description = cursor.getString(cursor.getColumnIndex("description"))
            val img = cursor.getString(cursor.getColumnIndex("img"))
            val repetitions = cursor.getString(cursor.getColumnIndex("repetitions"))
            val duration = cursor.getInt(cursor.getColumnIndex("duration"))
            val met = cursor.getDouble(cursor.getColumnIndex("met"))

            val workout = Workout(workout_id, img, name, description, repetitions, duration, met)

            list.add(workout)
        }

        cursor.close()

        return list
    }
}