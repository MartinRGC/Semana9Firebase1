package com.example.semana9firebase1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.semana9firebase1.R
import com.example.semana9firebase1.model.CourseModel
import com.squareup.picasso.Picasso

class CourseAdapter (private var lstCourses: List<CourseModel>):RecyclerView.Adapter<CourseAdapter.Viewholder>() {

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvDescription: TextView = itemView.findViewById(R.id.tvdescripcion)
        val tvscore: TextView = itemView.findViewById(R.id.tvscore)
        val ivnota: ImageView = itemView.findViewById(R.id.ivNota)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return Viewholder(layoutInflater.inflate(R.layout.item_course, parent, false))
    }

    override fun getItemCount(): Int {
        return lstCourses.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val itemCourse = lstCourses[position]
        holder.tvDescription.text = itemCourse.description
        holder.tvscore.text = itemCourse.score
        Picasso.get().load(itemCourse.imageUrl).into(holder.ivnota)
    }
}