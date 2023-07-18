package com.gofit.gofit

import android.content.Intent.getIntent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
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
    data class Item(val imageResId: Int, val text: String)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        when (DataManager.level) {
            1 -> label = "BEGINNER"
            2 -> label = "INTERMEDIATE"
            3 -> label = "ADVANCED"
        }

        val items = listOf(
            Item(R.drawable.imgbabs, "ABS $label"),
            Item(R.drawable.imgbarm, "ARMS $label"),
            Item(R.drawable.imgbabs, "CHEST $label"),
            Item(R.drawable.imgbshouldersback, "SHOULDERS AND BACK $label"),
            Item(R.drawable.imgbleg, "LEGS $label")
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

}
