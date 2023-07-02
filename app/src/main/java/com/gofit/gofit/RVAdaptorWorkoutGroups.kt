package com.gofit.gofit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdaptorWorkoutGroups(private val items: List<TrainingFragment.Item>) : RecyclerView.Adapter<RVAdaptorWorkoutGroups.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workoutgroup, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: TrainingFragment.Item = items[position]

        holder.image.setImageResource(item.imageResId)
        holder.text.text = item.text

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val item =  holder.adapterPosition
            var intent = Intent(context, WorkoutActivity::class.java)

            intent.putExtra("itemId", item) // Pass the necessary data to the second activity

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imgWorkout)
        val text: TextView = itemView.findViewById(R.id.tvWorkoutName)
    }
}
