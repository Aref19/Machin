package com.example.mechanic.Adpter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.R
import com.example.mechanic.model.Image
import com.example.mechanic.saveData.room.Item


class RecyclerAdpter(arrayofImages: ArrayList<Item>) :

    RecyclerView.Adapter<RecyclerAdpter.holder>() {

    var arrayofImages = arrayofImages

    class holder(item: View) : RecyclerView.ViewHolder(item) {
        var image1:ImageView?=null
        var text:TextView?=null
        init {
            image1=item.findViewById(R.id.imageitem)
            text=item.findViewById(R.id.nameitem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.itemrecycler1, parent, false)
        return holder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdpter.holder, position: Int) {
        holder.text!!.text=arrayofImages[position].id
        var bitmap=BitmapFactory.decodeFile(arrayofImages[position].file)
        holder.image1!!.setImageBitmap(bitmap)

    }

    override fun getItemCount(): Int {
        return arrayofImages.size
    }
}