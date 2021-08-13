package com.example.mechanic.ViewModels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mechanic.Interfacess.ImageInterface
import com.example.mechanic.model.Image
import com.example.mechanic.saveData.room.DaoItemsImages
import com.example.mechanic.saveData.room.DataBase
import com.example.mechanic.saveData.room.Item
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


class ImageLoadViewModel : ViewModel {
    var dao: DaoItemsImages? = null

    var imagemodel:Image?=null
    var image: ImageInterface? = null
    var live:LiveData<List<Item>>?=null

    constructor(image: ImageInterface, application: Application) {
        this.image = image
        dao = DataBase.getDatabase(application).dao()
         dao!!.delete()
        live=dao!!.getAll()
    }

    suspend fun loadImage(url: String) {
        delay(1000)

        Picasso.get().load(url).into(object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {

                image!!.lodedImage(bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {

            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

            }


        })


    }
    fun insertimage(item: Item){
        GlobalScope.launch(Dispatchers.Main){
            Log.i("item", "insertimage: " + item.id)
            dao!!.insertAll(item)


        }
    }

    // das ist dahab ahmar
    fun saveImagesinFile(bitmap: Bitmap,imagename:String){
        // creat file
        val direct = File(Environment.getExternalStorageDirectory().toString() + "/Foliate")//true
        Log.i("path", "saveImagesinFile: "+Environment.getExternalStorageDirectory().toString())
        // if file erlady here
        if (!direct.exists()) { //false
            val wallpaperDirectory = File("/sdcard/Foliate/")
            wallpaperDirectory.mkdirs()
        }

        val file = File("/sdcard/Foliate/", imagename+".jpg")
        if (file.exists()) {
            file.delete()
        }
        try {
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }



    }


}