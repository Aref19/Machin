package com.example.mechanic.ui

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.mechanic.Interfacess.ImageInterface
import com.example.mechanic.R
import com.example.mechanic.ViewModels.ImageLoadViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Picasso.LoadedFrom
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CentralActivty : AppCompatActivity(), ImageInterface {
    var image:ImageView?=null
    var view: ImageLoadViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central_activty)
        var button=findViewById<Button>(R.id.button)
        view= ImageLoadViewModel(this)
        image=findViewById(R.id.imageView)
        ActivityCompat.requestPermissions(
            this, arrayOf(
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE
            ), PackageManager.PERMISSION_GRANTED
        );

        button.setOnClickListener {

            CropImage.activity()
                .setScaleType(CropImageView.ScaleType.CENTER_CROP)


                .start(this);

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
             var imageCorp=CropImage.getActivityResult(data)
             if(resultCode== RESULT_OK){

                 GlobalScope.launch(Dispatchers.IO) {
                     view!!.loadImage(imageCorp.uri.toString())

                 }


             }
         }



        super.onActivityResult(requestCode, resultCode, data)
    }



    override fun lodedImage(bit: Bitmap?) {

           image!!.setImageBitmap(bit)

    }


}