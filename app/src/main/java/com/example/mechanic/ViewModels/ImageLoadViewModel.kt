package com.example.mechanic.ViewModels

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import com.example.mechanic.Interfacess.ImageInterface
import com.squareup.picasso.Picasso
import  com.squareup.picasso.Target
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.lang.Exception

class ImageLoadViewModel :ViewModel {
    var image:ImageInterface?=null
    constructor(image:ImageInterface){
        this.image=image

    }

  suspend  fun loadImage(url:String){

        Picasso.get().load(url).into(object :Target{
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                image!!.lodedImage(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }


        })


    }


}