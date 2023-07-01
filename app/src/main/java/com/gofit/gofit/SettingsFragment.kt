package com.gofit.gofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SettingsFragment : Fragment() {
    private lateinit var llEditProfile: LinearLayout
    private lateinit var llPrivacyPolicy: LinearLayout
    private lateinit var llLogOut: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        llEditProfile = rootView.findViewById(R.id.llEditProfile)
        llPrivacyPolicy = rootView.findViewById(R.id.llPrivacyPolicy)
        llLogOut = rootView.findViewById(R.id.llLogOut)

        llEditProfile.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragContent, EditProfileFragment())
                .addToBackStack(null) // This enables the "back" navigation
                .commit()
        }

        llPrivacyPolicy.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragContent, PrivacyPolicyFragment())
                .addToBackStack(null) // This enables the "back" navigation
                .commit()
        }

        llLogOut.setOnClickListener {
            // Handle item click here
        }

        return rootView
    }



}
