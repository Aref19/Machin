package com.example.mechanic.Adpter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Interfacess.RcyclerPosition
import com.example.mechanic.R
import com.example.mechanic.model.Image
import com.example.mechanic.saveData.room.Item
import com.example.mechanic.xmlTools.RoundedImage
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File


class RecyclerAdpter(arrayofImages: ArrayList<Item>,rcyclerPosition: RcyclerPosition) :

    RecyclerView.Adapter<RecyclerAdpter.holder>() {
    var rcyclerPosition =rcyclerPosition
    var arrayofImages = arrayofImages

    class holder(item: View,rcyclerPosition: RcyclerPosition) : RecyclerView.ViewHolder(item) {
        var image1: ImageView? = null
        var text: TextView? = null
        var rcyclerPosition=rcyclerPosition

        init {
            image1 = item.findViewById(R.id.imageitem)
            text = item.findViewById(R.id.nameitem)
            var text1=item.findViewById<TextView>(R.id.pup)

            item.setOnClickListener { v->
                Log.i("idfrom", ": "+(item.id==text1.id)+"v :"+v.id+"tex1 :"+text1.id)
                if(v.id==R.id.pup){
                    rcyclerPosition.getPostionSelected(v)
                    Log.i("id1", ": "+v.id)
                }else if (v.id==text!!.id){
                    rcyclerPosition.getPostionSelected((adapterPosition),"1")
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.itemrecycler1, parent, false)
        return holder(view,rcyclerPosition)
    }

    override fun onBindViewHolder(holder: RecyclerAdpter.holder, position: Int) {
        holder.text!!.text = arrayofImages[position].id
         var bitmap:Bitmap?=null

        Picasso.get().load(File(arrayofImages[position].file)).transform(RoundedCornersTransformation(10,10)).centerCrop()
            .resize(250, 150).into(holder.image1)
        //holder.image1!!.setImageDrawable( Drawable.createFromPath(arrayofImages[position].file))
        /*
        bitmap=BitmapFactory.decodeFile(arrayofImages[position].file)
        holder.image1!!.setImageBitmap(bitmap)

        Drawable.createFromPath(arrayofImages[position].file)

        Picasso.get().load("file/"arrayofImages[position].file.toString().trim()).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                holder.image1!!.setImageBitmap(bitmap)
                Log.i("bitmap1s", "onBitmapLoaded: "+bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                Log.i("why", "onBitmapFailed: "+e)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }


        })

        */

        //bitmap = BitmapFactory.decodeFile(arrayofImages[position].file)

                //showPictures(holder, bitmap)


    }

    override fun getItemCount(): Int {
        return arrayofImages.size
    }



}