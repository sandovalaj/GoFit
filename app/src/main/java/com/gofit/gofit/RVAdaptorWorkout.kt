package com.gofit.gofit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdaptorWorkout(private val items: List<WorkoutActivity.Item>) : RecyclerView.Adapter<RVAdaptorWorkout.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: WorkoutActivity.Item = items[position]

        holder.imgWorkout.setImageResource(item.imgId)
        holder.workoutName.text = item.workoutName
        holder.workoutInfo.text = item.workoutInfo

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val item =  holder.adapterPosition
            var intent = Intent(context, ExerciseActivity::class.java)

            intent.putExtra("itemId", item) // Pass the necessary data to the second activity

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgWorkout: ImageView = itemView.findViewById(R.id.imgWorkout)
        val workoutName: TextView = itemView.findViewById(R.id.tvWorkoutName)
        val workoutInfo: TextView = itemView.findViewById(R.id.tvInfo)
    }
}
