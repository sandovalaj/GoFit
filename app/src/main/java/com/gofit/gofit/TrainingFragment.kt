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
    private var label: String = ""
    private lateinit var db: SQLiteDatabase

    data class Item(val imageResId: Int, val text: String, val workouts: MutableList<Workout>)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var databaseHelper = DatabaseHelper(requireContext())
        db = databaseHelper.openDatabase()

        var all: MutableList<MutableList<Workout>> = mutableListOf()
        var list : MutableList<Workout> = mutableListOf()

        for (i in 1..5) {
            list = search(i)
            all.add(list)
        }

        when (DataManager.level) {
            1 -> label = "BEGINNER"
            2 -> label = "INTERMEDIATE"
            3 -> label = "ADVANCED"
        }

        val items = listOf(
            Item(R.drawable.imgbabs, "ABS $label", all[0]),
            Item(R.drawable.imgbarm, "ARMS $label", all[1]),
            Item(R.drawable.imgbabs, "CHEST $label", all[2]),
            Item(R.drawable.imgbleg, "LEGS $label", all[3]),
            Item(R.drawable.imgbleg, "SHOULDERS AND BACK $label", all[4])
        )

        val rootView = inflater.inflate(R.layout.fragment_training, container, false)
        rvWorkouts = rootView.findViewById(R.id.rvWorkoutGroups)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvWorkouts.layoutManager = layoutManager

        val adapter = RVAdaptorWorkoutGroups(items)
        rvWorkouts.adapter = adapter

        tvWorkoutLabel = rootView.findViewById(R.id.tvWorkoutLabel)
        tvWorkoutLabel.text = label
        return rootView
    }

    @SuppressLint("Range")
    fun search(i: Int): MutableList<Workout> {
        var query = "SELECT * FROM Workouts w WHERE fitness_goal_id = ? AND physical_level_id = ? AND target_zone_id = ?"
        var selectionArgs = arrayOf(DataManager.goal.toString(), DataManager.level.toString(), i.toString())
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

