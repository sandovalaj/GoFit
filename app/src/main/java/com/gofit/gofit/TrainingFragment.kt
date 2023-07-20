package com.gofit.gofit

import Workout
import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingFragment : Fragment() {
    private lateinit var rvWorkouts: RecyclerView
    private lateinit var tvWorkoutLabel: TextView
    private lateinit var db: SQLiteDatabase

    data class Item(val imageResId: Int, val text: String, val workouts: MutableList<Workout>)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var all: MutableList<MutableList<Workout>> = mutableListOf()
        var list : MutableList<Workout> = mutableListOf()

        var items = mutableListOf<Item>()
        var levelLabel = ""
        var targetLabel = ""
        var resID = 0

        for (level in 1..3) {
            when (level) {
                1 -> levelLabel = "BEGINNER"
                2 -> levelLabel = "INTERMEDIATE"
                3 -> levelLabel = "ADVANCED"
            }

            for (target in 1..5) {
                when (target) {
                    1 -> {
                        targetLabel = "ABS"
                        resID = resources.getIdentifier("home_abs", "drawable", requireContext().packageName)
                    } 2 -> {
                        targetLabel = "ARMS"
                        resID = resources.getIdentifier("home_arm", "drawable", requireContext().packageName)
                    } 3 -> {
                        targetLabel = "CHEST"
                        resID = resources.getIdentifier("home_chest", "drawable", requireContext().packageName)
                    } 4 -> {
                        targetLabel = "LEGS"
                        resID = resources.getIdentifier("home_leg", "drawable", requireContext().packageName)
                    } 5 -> {
                        targetLabel = "SHOULDERS AND BACK"
                        resID = resources.getIdentifier("home_shoulderandback", "drawable", requireContext().packageName)
                    }
                }

                list = search(level, target)
                val item = Item(resID, "$targetLabel $levelLabel", list)
                items.add(item)
            }
        }

        val rootView = inflater.inflate(R.layout.fragment_training, container, false)
        rvWorkouts = rootView.findViewById(R.id.rvWorkoutGroups)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvWorkouts.layoutManager = layoutManager

        val adapter = RVAdaptorWorkoutGroups(items)
        rvWorkouts.adapter = adapter

        tvWorkoutLabel = rootView.findViewById(R.id.tvWorkoutLabel)
        tvWorkoutLabel.text = "Test"
        return rootView
    }

    @SuppressLint("Range")
    fun search(level: Int, target: Int): MutableList<Workout> {
        var databaseHelper = DatabaseHelper(requireContext())
        db = databaseHelper.openDatabase()

        var query = "SELECT * FROM Home h WHERE physical_level_id = ? AND target_zone_id = ?"
        var selectionArgs = arrayOf(level.toString(), target.toString())
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

