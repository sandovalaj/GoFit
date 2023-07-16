package com.gofit.gofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class EditProfileFragment : Fragment() {
    private lateinit var etFName: EditText
    private lateinit var etLName: EditText
    private lateinit var etBirthday: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var btnEditSave: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_editprofile, container, false)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        etFName = rootView.findViewById(R.id.etFName)
        etLName = rootView.findViewById(R.id.etLName)
        etBirthday = rootView.findViewById(R.id.etBirthday)

        etFName.setText(DataManager.fname)
        etLName.setText(DataManager.lname)
        etBirthday.setText(DataManager.birthday)

        return rootView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
                actionBar?.setDisplayHomeAsUpEnabled(false)

                requireActivity().onBackPressed() // Handle back button click event
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
