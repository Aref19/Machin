package com.example.mechanic.ui

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Adpter.RecyclerAdpter
import com.example.mechanic.Interfacess.ImageInterface
import com.example.mechanic.R
import com.example.mechanic.ViewModels.ImageLoadViewModel
import com.example.mechanic.model.Image
import com.example.mechanic.saveData.room.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class CentralActivty : AppCompatActivity(), ImageInterface {
    var image: ImageView? = null
    var viewmodel: ImageLoadViewModel? = null
    var fabbutton: FloatingActionButton? = null
    var rescler:RecyclerView?=null
    var rescler2:RecyclerView?=null
    var items= ArrayList<Item>()
    val fragmentManager=supportFragmentManager.beginTransaction()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central_activty)
        fabbutton = findViewById(R.id.floating_action_button)
        viewmodel = ImageLoadViewModel(this,application)
         rescler=findViewById(R.id.order_1)
        rescler2=findViewById(R.id.images_2)

        ActivityCompat.requestPermissions(
            this, arrayOf(
                WRITE_EXTERNAL_STORAGE,
                READ_EXTERNAL_STORAGE
            ), PackageManager.PERMISSION_GRANTED
        );


        fabbutton!!.setOnClickListener {

            CropImage.activity()
                .setScaleType(CropImageView.ScaleType.CENTER_CROP)
                .start(this)





        }
  /*
        viewmodel!!.live!!.observe(this, {
            items.addAll(it)

            rescler!!.adapter = RecyclerAdpter(items!!)
            rescler!!.layoutManager = LinearLayoutManager(applicationContext)
        })

   */








    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            var imageCorp = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {

                GlobalScope.launch(Dispatchers.Main) {
                    Upop().show(fragmentManager,null)

                    viewmodel!!.loadImage(imageCorp.uri.toString())

                }


            }
        }



        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun lodedImage(bit: Bitmap?) {

          viewmodel!!.insertimage(Item("erste",Upop().getnameFromuser()))
          viewmodel!!.saveImagesinFile(bit!!,Upop().getnameFromuser())
    }


}