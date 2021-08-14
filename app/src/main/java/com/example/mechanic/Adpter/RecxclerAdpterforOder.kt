package com.example.mechanic.Adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.R

class RecxclerAdpterforOder(item:ArrayList<String>) : RecyclerView.Adapter<RecxclerAdpterforOder.holder>() {
  var item=item
    class holder(item: View) : RecyclerView.ViewHolder(item) {
        var image1: ImageView? = null
        var text: TextView? = null

        init {
            image1 = item.findViewById(R.id.imgorder)
            text = item.findViewById(R.id.nameorder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecxclerAdpterforOder.holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return RecxclerAdpterforOder.holder(view)
    }



    override fun getItemCount(): Int {
      return item.size
    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        holder.image1!!.setImageResource(R.drawable.ic_baseline_folder_24)
        holder.text!!.text = item[position]

    }
}