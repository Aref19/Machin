package com.example.mechanic.Adpter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Interfacess.RcyclerPosition
import com.example.mechanic.R
import com.example.mechanic.model.Image
import com.example.mechanic.saveData.room.Item
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.tabeladpter.view.*
import java.io.File

class RecyclerAdpterForTableandAll(
    arraylistofimages: ArrayList<Item>,
    recyclerpo: RcyclerPosition
) :
    RecyclerView.Adapter<RecyclerAdpterForTableandAll.holder>() {
    var recyclerpo = recyclerpo
    var arraylistofimages = arraylistofimages

    class holder(v: View, recyclerpo: RcyclerPosition) : RecyclerView.ViewHolder(v) {
        var imageView = v.findViewById<ImageView>(R.id.imageView1)
        var imageView1 = v.findViewById<ImageView>(R.id.imageView1)
        var recyclerpo = recyclerpo

        init {
            v.setOnClickListener { v ->

                recyclerpo.getPostionSelected((adapterPosition + 1), "3")

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.tabeladpter, parent, false)
        return RecyclerAdpterForTableandAll.holder(view, recyclerpo)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: holder, position: Int) {
        if (arraylistofimages.size > 0) {
            Picasso.get().load(File(arraylistofimages[position].file)).transform(
                RoundedCornersTransformation(10, 10)
            ).centerCrop()
                .resize(300, 300).into(holder.imageView)

            if (arraylistofimages.size >= 1) {
                Log.i("war", "onBindViewHolder: " + "war")

                Picasso.get().load(File(arraylistofimages[position].file)).transform(
                    RoundedCornersTransformation(10, 10)
                )
                    .into(holder.imageView1)
            }
        }

    }

    override fun getItemCount(): Int {
        return arraylistofimages.size
    }
}