package com.gofit.gofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class EditProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_editprofile, container, false)

        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

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
