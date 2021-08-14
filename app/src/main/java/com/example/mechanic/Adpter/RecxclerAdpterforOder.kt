package com.example.mechanic.Adpter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.R

class RecxclerAdpterforOder : RecyclerView.Adapter<RecyclerAdpter.holder>() {


    class holder(item: View) : RecyclerView.ViewHolder(item) {
        var image1: ImageView? = null
        var text: TextView? = null

        init {
            image1 = item.findViewById(R.id.imageitem)
            text = item.findViewById(R.id.nameitem)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdpter.holder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerAdpter.holder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}