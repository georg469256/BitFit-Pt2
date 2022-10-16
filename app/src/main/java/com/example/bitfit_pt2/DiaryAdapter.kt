package com.example.bitfit_pt2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DiaryAdapter(private val mDiaries: List<DisplayDiary>) : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val diaryTitle: TextView = itemView.findViewById(R.id.DiaryTitleText)
        val diaryDate: TextView = itemView.findViewById(R.id.DiaryEntryDateText)
        val diaryEntry: TextView = itemView.findViewById(R.id.DiaryEntryText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val itemView = inflater.inflate(R.layout.diary_entry, parent, false)
        // Return a new holder instance
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val diary = mDiaries[position]
        holder.diaryTitle.text = diary.title.toString()
        holder.diaryDate.text = diary.date.toString().substring(0, diary.date.toString().indexOf("T"))
        holder.diaryEntry.text = diary.entry
    }

    override fun getItemCount(): Int {
        return mDiaries.size
    }


}