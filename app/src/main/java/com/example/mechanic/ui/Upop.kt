package com.example.mechanic.ui

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.audiofx.AudioEffect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mechanic.Interfacess.ImageInterface
import com.example.mechanic.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Upop : DialogFragment, View.OnClickListener {
    var vie: View? = null
    var button: Button? = null
    var text: EditText? = null
    var name: String? = null
    var url: String? = null
    var nameforder: String? = ""
    var image: ImageInterface? = null
    var accses: Boolean? = null

    constructor(image: ImageInterface, url: String,accsess:Boolean) {
        this.image = image
        this.url = url
        this.accses=accsess
    }

    constructor(image: ImageInterface,accsess:Boolean) {
        this.image = image
        this.accses=accsess
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vie = inflater.inflate(R.layout.upop, container, false)
        button = vie!!.findViewById(R.id.nameselect)
        text = vie!!.findViewById(R.id.nameimgafile)
        button!!.setOnClickListener(this)
        return vie
    }

    override fun onClick(p0: View?) {
        if(accses!!){
            name = text!!.text.toString().trim()
            getnameFromuser()
            loadImage(url)
        }else{
            nameforder = text!!.text.toString().trim()

            if (!nameforder.equals("")) {
                image!!.mkorder(nameforder!!)
            }
        }








        this.dismiss()
    }

    fun loadImage(url: String?) {


        Picasso.get().load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                image!!.lodedImage(bitmap, getnameFromuser())
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }


        })


    }

    fun getnameFromuser(): String =
        name!!


}