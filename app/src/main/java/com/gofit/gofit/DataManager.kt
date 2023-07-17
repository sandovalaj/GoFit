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
                birthday = documentSnapshot.getString("birthday").toString()
                gender = documentSnapshot.getLong("gender")!!.toInt()
                height = documentSnapshot.getLong("height")!!.toInt()
                weight = documentSnapshot.getLong("weight")!!.toInt()
                goal = documentSnapshot.getLong("goal")!!.toInt()
                level = documentSnapshot.getLong("level")!!.toInt()
                favorites = documentSnapshot.get("favorites") as MutableList<Int>
                discover = documentSnapshot.get("discover") as MutableList<Int>
                workouts = documentSnapshot.get("workouts") as MutableList<Int>
            } else {
                Log.e("Hatdog", "The document does not exist.");
            }
        }?.addOnFailureListener { e ->
            Log.e("Hatdog", "Error fetching data", e);
        }
    }

    // Update Data Manager
    fun updateDataManager() {
//        val db = FirebaseFirestore.getInstance()
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        val userId = currentUser?.uid
//
//        val userInfoRef = userId?.let { db.collection("user_info").document(it) }
//        userInfoRef?.get()?.addOnSuccessListener { documentSnapshot ->
//            if (documentSnapshot.exists()) {
//                // Document exists, retrieve the data
//                fname = documentSnapshot.getString("fname").toString()
//                lname = documentSnapshot.getString("lname").toString()
//                birthday = documentSnapshot.getString("birthday") as String
//                gender = documentSnapshot.getLong("gender")!!.toInt()
//                height = documentSnapshot.getLong("height")!!.toInt()
//                weight = documentSnapshot.getLong("weight")!!.toInt()
//                goal = documentSnapshot.getLong("goal")!!.toInt()
//                level = documentSnapshot.getLong("level")!!.toInt()
//                favorites = documentSnapshot.get("favorites") as MutableList<Int>
//                discover = documentSnapshot.get("discover") as MutableList<Int>
//                workouts = documentSnapshot.get("workouts") as MutableList<Int>
//            } else {
//                Log.e("Hatdog", "The document does not exist.");
//            }
//        }?.addOnFailureListener { e ->
//            Log.e("Hatdog", "Error fetching data", e);
//        }
    }

    fun addDocument() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val userInfo = hashMapOf(
                "fname" to fname,
                "lname" to lname,
                "gender" to gender,
                "birthday" to birthday,
                "height" to height,
                "weight" to weight,
                "goal" to goal,
                "level" to level,
                "workouts" to workouts,
                "favorites" to favorites,
                "discover" to discover
                // Add other user information fields as needed
            )

            // Add the user information to Firestore
            db.collection("user_info")
                .document(userId)
                .set(userInfo)
                .addOnFailureListener { e ->
                    // An error occurred while adding the document
                    // Handle the error here
                    Log.e("Hatdog", "Error handling data", e);
                }
        }
    }

    fun clearDataManager() {
        fname = ""
        lname = ""
        gender = 0
        birthday = ""
        height = 0
        weight = 0
        goal = 0
        level = 0
        workouts.clear()
        favorites.clear()
        discover.clear()
    }
}
