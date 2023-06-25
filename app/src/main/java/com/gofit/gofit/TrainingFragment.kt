package com.gofit.gofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TrainingFragment : Fragment() {
    private lateinit var rvWorkouts: RecyclerView
    data class Item(val imageResId: Int, val text: String)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // List of Workout Groups
        val items = listOf(
            Item(R.drawable.imgbabs, "ABS BEGINNER"),
            Item(R.drawable.imgbarm, "ARMS BEGINNER"),
            Item(R.drawable.imgbabs, "CHEST BEGINNER"),
            Item(R.drawable.imgbshouldersback, "SHOULDERS AND BACK BEGINNER"),
            Item(R.drawable.imgbleg, "LEGS BEGINNER")
        )

        val rootView = inflater.inflate(R.layout.fragment_training, container, false)
        rvWorkouts = rootView.findViewById(R.id.rvWorkouts)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvWorkouts.layoutManager = layoutManager

        val adapter = RecyclerViewAdapter(items)
        rvWorkouts.adapter = adapter

        return rootView
    }

}
