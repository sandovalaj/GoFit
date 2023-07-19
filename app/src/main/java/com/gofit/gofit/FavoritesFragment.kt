package com.gofit.gofit

import Workout
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoritesFragment : Fragment() {
    private lateinit var rvForYouWorkouts: RecyclerView
    private lateinit var rvFaveWorkouts: RecyclerView

    private lateinit var tvGoalText: TextView
    private lateinit var tvLevelText: TextView
    private lateinit var emptyListLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val itemsForYou = listOf(
            TrainingFragment.Item(R.drawable.imgbabs, "START YOUR WORKOUT", DataManager.workouts)
        )

        val rootView = inflater.inflate(R.layout.fragment_favorites, container, false)

        tvGoalText = rootView.findViewById(R.id.tvGoalText)
        tvLevelText = rootView.findViewById(R.id.tvLevelText)
        emptyListLayout = rootView.findViewById(R.id.emptyListLayout)

        var goalLabel = ""
        var levelLabel = ""

        when (DataManager.goal) {
            1 -> goalLabel = "BEGINNER"
            2 -> goalLabel = "INTERMEDIATE"
            3 -> goalLabel = "ADVANCED"
        }

        when (DataManager.level) {
            1 -> levelLabel = "BEGINNER"
            2 -> levelLabel = "INTERMEDIATE"
            3 -> levelLabel = "ADVANCED"
        }

        tvGoalText.text = goalLabel
        tvLevelText.text = levelLabel

        rvForYouWorkouts = rootView.findViewById(R.id.rvForYouWorkouts)
        val layoutManagerForYou: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvForYouWorkouts.layoutManager = layoutManagerForYou
        val adapterForYou = RVAdaptorWorkoutGroups(itemsForYou)
        rvForYouWorkouts.adapter = adapterForYou

        val itemsFavorites : MutableList<WorkoutActivity.Item> = mutableListOf()
        var numberWorkouts = 0
        var totalDuration = 0

        if (DataManager.favorites != null) {
            for (workout in DataManager.favorites) {
                var drawable: Drawable? = null
                var name = ""
                var reps = ""
                val w: Workout

                val resID = resources.getIdentifier(workout.img, "drawable", requireContext().packageName)

                name = workout.name
                reps = workout.repetitions
                w = workout

                itemsFavorites.add(WorkoutActivity.Item(resID, name, reps, w))

                numberWorkouts += 1
                totalDuration += workout.duration
            }
        }

        var temp = totalDuration.toDouble() / 60
        temp = Math.ceil(temp)

        val t = "$numberWorkouts workouts | $temp minutes"

        rvFaveWorkouts = rootView.findViewById(R.id.rvFaveWorkouts)
        val layoutManagerFaves: RecyclerView.LayoutManager = LinearLayoutManager(context)
        rvFaveWorkouts.layoutManager = layoutManagerFaves
        val adapterFaves = RVAdaptorWorkout(itemsFavorites, numberWorkouts, DataManager.favorites as ArrayList<Workout>)
        rvFaveWorkouts.adapter = adapterFaves

        // Check if the list is empty and show/hide the "Please add" page accordingly
        if (DataManager.favorites.isEmpty()) {
            rvFaveWorkouts.visibility = View.GONE
            emptyListLayout.visibility = View.VISIBLE
        } else {
            rvFaveWorkouts.visibility = View.VISIBLE
            emptyListLayout.visibility = View.GONE
        }

        return rootView
    }
}
