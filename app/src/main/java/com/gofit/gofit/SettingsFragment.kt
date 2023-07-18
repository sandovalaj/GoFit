package com.gofit.gofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

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
            var intent = Intent(requireActivity(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        llPrivacyPolicy.setOnClickListener {
            // handle item click here
        }

        llLogOut.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            auth.signOut()

            var intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(requireContext(), "Successfully logged out.", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }



}
