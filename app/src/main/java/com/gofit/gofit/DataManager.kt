package com.gofit.gofit

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User

object DataManager {
    var fname: String = ""
    var lname: String = ""
    var gender: Int = 0
    var birthday: String = ""
    var height: Int = 0
    var weight: Int = 0
    var goal: Int = 0
    var target: Int = 0
    var level: Int = 0
    var workouts: MutableList<Int> = mutableListOf()
    var favorites: MutableList<Int> = mutableListOf()
    var discover: MutableList<Int> = mutableListOf()

//    Fetches data from FireStore and places in DataManager of local device
    fun fetchUserData() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        val userInfoRef = userId?.let { db.collection("user_info").document(it) }
        userInfoRef?.get()?.addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                // Document exists, retrieve the data
                fname = documentSnapshot.getString("fname").toString()
                lname = documentSnapshot.getString("lname").toString()
                birthday = documentSnapshot.getString("birthday") as String
                gender = documentSnapshot.getLong("gender")!!.toInt()
                height = documentSnapshot.getLong("height")!!.toInt()
                weight = documentSnapshot.getLong("weight")!!.toInt()
                goal = documentSnapshot.getLong("goal")!!.toInt()
                level = documentSnapshot.getLong("level")!!.toInt()
                target = documentSnapshot.getLong("target")!!.toInt()
                favorites = documentSnapshot.get("favorites") as MutableList<Int>
                discover = documentSnapshot.get("discover") as MutableList<Int>
                workouts = documentSnapshot.get("workouts") as MutableList<Int>
            } else {
                // Document does not exist
                // Handle this scenario if needed
                Log.e("Hatdog", "The document does not exist.");
            }
        }?.addOnFailureListener { e ->
            // An error occurred while fetching data
            // Handle the error here
            Log.e("Hatdog", "Error fetching data", e);
        }
    }

    // Update Data Manager
    fun updateDataManager(user: User?) {
        if (user != null) {

        } else {
            // Handle the case when the user data is not available
            // For example, clear the DataManager or set default values
        }
    }}
