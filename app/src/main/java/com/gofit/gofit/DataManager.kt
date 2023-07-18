package com.gofit.gofit

import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.Timestamp

object DataManager {
    var fname: String = ""
    var mname: String = ""
    var lname: String = ""
    var gender: Int = 0
    var civil: Int = 0
    var address: String = ""
    var birthday: Timestamp? = null
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
                mname = documentSnapshot.getString("mname").toString()
                lname = documentSnapshot.getString("lname").toString()
                address = documentSnapshot.getString("address").toString()
                birthday = documentSnapshot.get("birthday") as Timestamp
                civil = documentSnapshot.getLong("civil")!!.toInt()
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
    fun updateDataManager(): Boolean {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userID = currentUser?.uid

        if (userID != null) {
            val updates = hashMapOf(
                "fname" to fname,
                "mname" to mname,
                "lname" to lname,
                "address" to address,
                "civil" to civil,
                "gender" to gender,
                "birthday" to birthday,
                "height" to height,
                "weight" to weight,
                "goal" to goal,
                "level" to level,
                "workouts" to workouts,
                "favorites" to favorites,
                "discover" to discover
            )

            return try {
                db.collection("user_info")
                    .document(userID)
                    .update(updates)
                    true // Update was successful
            } catch (e: Exception) {
                // Update failed, handle the error if needed
                Log.e("Hatdog", "Error updating document", e)
                false
            }
        } else {
            Log.e("Hatdog", "User not found in DB")
            return false
        }

    }

    fun addDocument() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val userInfo = hashMapOf(
                "fname" to fname,
                "mname" to mname,
                "lname" to lname,
                "address" to address,
                "civil" to civil,
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
        } else {
            Log.e("Hatdog", "User does not exist in DB");
        }
    }

    fun clearDataManager() {
        fname = ""
        mname = ""
        lname = ""
        address = ""
        gender = 0
        civil = 0
        birthday = null
        height = 0
        weight = 0
        goal = 0
        level = 0
        workouts.clear()
        favorites.clear()
        discover.clear()
    }
}
