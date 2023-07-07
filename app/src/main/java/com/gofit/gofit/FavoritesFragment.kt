package com.gofit.gofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesFragment : Fragment() {
    private lateinit var rvForYouWorkouts: RecyclerView
    private lateinit var rvFaveWorkouts: RecyclerView
    data class Item(val imageResId: Int, val text: String)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // List of Workout Groups
        val items = listOf(
            TrainingFragment.Item(R.drawable.imgbabs, "ABS BEGINNER"),
            TrainingFragment.Item(R.drawable.imgbarm, "ARMS BEGINNER"),
            TrainingFragment.Item(R.drawable.imgbabs, "CHEST BEGINNER"),
            TrainingFragment.Item(R.drawable.imgbshouldersback, "SHOULDERS AND BACK BEGINNER"),
            TrainingFragment.Item(R.drawable.imgbleg, "LEGS BEGINNER")
        )

        val rootView = inflater.inflate(R.layout.fragment_favorites, container, false)

        rvForYouWorkouts = rootView.findViewById(R.id.rvForYouWorkouts)
        rvFaveWorkouts = rootView.findViewById(R.id.rvFaveWorkouts)

        val layoutManagerForYou: RecyclerView.LayoutManager = LinearLayoutManager(context)
        val layoutManagerFaves: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvForYouWorkouts.layoutManager = layoutManagerForYou
        rvFaveWorkouts.layoutManager = layoutManagerFaves

        val adapterForYou = RVAdaptorWorkoutGroups(items)
        val adapterFaves = RVAdaptorWorkoutGroups(items)
        rvForYouWorkouts.adapter = adapterForYou
        rvFaveWorkouts.adapter = adapterFaves

        return rootView

        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }
}
