package com.example.myapplication4

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class DiaryAdapter(
    private val items: List<DiaryEntry>
) : RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>() {

    inner class DiaryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvDateTime: TextView = view.findViewById(R.id.tvDateTime)
        val tvLocation: TextView = view.findViewById(R.id.tvLocation)
        val tvThrow: TextView = view.findViewById(R.id.tvThrow)
        val tvReward: TextView = view.findViewById(R.id.tvReward)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_diary, parent, false)
        return DiaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        val entry = items[position]
        holder.tvDateTime.text = entry.dateTime
        holder.tvLocation.text = entry.location
        holder.tvThrow.text = "Throw! ${entry.throwItem}"
        holder.tvReward.text = "Reward +${entry.exp} EXP, +${entry.coin} Points"
    }

    override fun getItemCount() = items.size
}
