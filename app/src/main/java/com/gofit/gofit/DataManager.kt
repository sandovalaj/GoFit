package com.gofit.gofit

import Workout
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp

interface UserDataCallback {
    fun onUserDataFetched()
    fun onFetchError(e: Exception)
}

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
    var workouts: MutableList<Workout> = mutableListOf()
    var favorites: MutableList<Workout> = mutableListOf()

//    Fetches data from FireStore and places in DataManager of local device
    fun fetchUserData(callback: LoginActivity) {
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
                val favoritesList = documentSnapshot.get("favorites") as? List<HashMap<String, Any>>
                favorites = favoritesList?.map { favoritesMap ->
                    Workout(
                        (favoritesMap["id"] as Long).toInt(),
                        favoritesMap["img"] as String,
                        favoritesMap["name"] as String,
                        favoritesMap["description"] as String,
                        favoritesMap["repetitions"] as String,
                        (favoritesMap["duration"] as Long).toInt(),
                        favoritesMap["met"] as Double
                    )
                }?.toMutableList() ?: mutableListOf()

                val workoutList = documentSnapshot.get("workouts") as? List<HashMap<String, Any>>
                workouts = workoutList?.map { workoutMap ->
                    Workout(
                        (workoutMap["id"] as Long).toInt(),
                        workoutMap["img"] as String,
                        workoutMap["name"] as String,
                        workoutMap["description"] as String,
                        workoutMap["repetitions"] as String,
                        (workoutMap["duration"] as Long).toInt(),
                        workoutMap["met"] as Double
                    )
                }?.toMutableList() ?: mutableListOf()

                callback.onUserDataFetched()
            } else {
                Log.e("DataManager.kt", "The document does not exist.");
            }
        }?.addOnFailureListener { e ->
            Log.e("DataManager.kt", "Error fetching data", e);
            callback.onFetchError(e)
        }

    }

    // Update Data Manager
    fun updateDataManager(): Boolean {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userID = currentUser?.uid

        val workoutList = workouts.map {
            hashMapOf(
                "id" to it.id,
                "img" to it.img,
                "name" to it.name,
                "description" to it.description,
                "repetitions" to it.repetitions,
                "duration" to it.duration,
                "met" to it.met
            )
        }

        val favoritesList = favorites.map {
            hashMapOf(
                "id" to it.id,
                "img" to it.img,
                "name" to it.name,
                "description" to it.description,
                "repetitions" to it.repetitions,
                "duration" to it.duration,
                "met" to it.met
            )
        }

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
                "workouts" to workoutList,
                "favorites" to favoritesList,
            )

            return try {
                db.collection("user_info")
                    .document(userID)
                    .update(updates)
                    true // Update was successful
            } catch (e: Exception) {
                // Update failed, handle the error if needed
                Log.e("DataManager.kt", "Error updating document", e)
                false
            }
        } else {
            Log.e("DataManager.kt", "User not found in DB")
            return false
        }

    }

    fun addDocument() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        val workoutList = workouts.map {
            hashMapOf(
                "id" to it.id,
                "img" to it.img,
                "name" to it.name,
                "description" to it.description,
                "repetitions" to it.repetitions,
                "duration" to it.duration,
                "met" to it.met
            )
        }

        val favoritesList = favorites.map {
            hashMapOf(
                "id" to it.id,
                "img" to it.img,
                "name" to it.name,
                "description" to it.description,
                "repetitions" to it.repetitions,
                "duration" to it.duration,
                "met" to it.met
            )
        }

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
                "workouts" to workoutList,
                "favorites" to favoritesList,
                // Add other user information fields as needed
            )

            // Add the user information to Firestore
            db.collection("user_info")
                .document(userId)
                .set(userInfo)
                .addOnFailureListener { e ->
                    // An error occurred while adding the document
                    // Handle the error here
                    Log.e("DataManager.kt", "Error handling data", e);
                }
        } else {
            Log.e("DataManager.kt", "User does not exist in DB");
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
    }
}
