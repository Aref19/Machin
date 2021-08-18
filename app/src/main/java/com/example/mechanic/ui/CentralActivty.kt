package com.example.mechanic.ui

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Adpter.RecxclerAdpterforOder
import com.example.mechanic.Adpter.RecyclerAdpter
import com.example.mechanic.Interfacess.ImageInterface
import com.example.mechanic.Interfacess.RcyclerPosition
import com.example.mechanic.R
import com.example.mechanic.ViewModels.ImageLoadViewModel
import com.example.mechanic.saveData.room.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.coroutines.*


class CentralActivty : AppCompatActivity(), ImageInterface, RcyclerPosition ,PopupMenu.OnMenuItemClickListener{
    var image: ImageView? = null
    var viewmodel: ImageLoadViewModel? = null
    var fabbutton: FloatingActionButton? = null
    var rescler: RecyclerView? = null
    var rescler2: RecyclerView? = null
    var items_1 = ArrayList<Item>()
    var itemorder = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_central_activty)
        fabbutton = findViewById(R.id.floating_action_button)
        viewmodel = ImageLoadViewModel(application)
        rescler = findViewById(R.id.order_1)
        rescler2 = findViewById(R.id.images_2)


        rescler!!.layoutManager = LinearLayoutManager(applicationContext)
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
        viewmodel!!.live?.observe(this, {

            items_1.clear()
            items_1.addAll(it)

            rescler2!!.adapter = RecyclerAdpter(items_1!!, this)

            rescler2!!.layoutManager = LinearLayoutManager(applicationContext)


        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            var imageCorp = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {

                GlobalScope.launch(Dispatchers.Main) {
                    var fragmentManager = supportFragmentManager.beginTransaction()
                    Upop(this@CentralActivty, imageCorp.uri.toString(), true).show(
                        fragmentManager,
                        null
                    )


                    // viewmodel!!.loadImage(imageCorp.uri.toString())


                }
            }



            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    override fun lodedImage(bit: Bitmap?, name: String) {


        viewmodel!!.saveImagesinFile(bit!!, name)
        viewmodel!!.insertimage(
            Item(
                name,
                Environment.getExternalStorageDirectory()
                    .toString() + "/Foliate/" + name + ".jpg".trim()
            )
        )


    }

    override fun mkorder(nameorder: String) {

        Log.i("nameordr", "mkorder: " + nameorder)
        itemorder.add(nameorder)
        rescler!!.adapter = RecxclerAdpterforOder(itemorder!!, this)

        rescler!!.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.addneworder, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (itemorder.size >= 2) {
            var alert = AlertDialog.Builder(this)
                .setTitle("Ops")
                .setMessage("you can not creat more as Tow Folder").show()
        } else {
            when (item.itemId) {
                R.id.addnewitem -> {
                    Log.i("menu", "onOptionsItemSelected: " + "das")

                    var fragmentManager = supportFragmentManager.beginTransaction()
                    Upop(this, false).show(fragmentManager, null)


                }
            }
        }

        return super.onOptionsItemSelected(item)
    }


    override fun getPostionSelected(po: Int, from: String) {
        var bundle=Bundle()

        if (from == "1") {
            Toast.makeText(this, items_1!!.get(po).id.toString(), Toast.LENGTH_LONG).show()
            bundle.putString("nameofitem", items_1!!.get(po).id)
            startActivity(
                Intent(
                    this, PictareOfFolder::class.java


                ).putExtras(bundle)
            )
        } else if (from == "2") {
            Toast.makeText(this, itemorder!!.get(po).toString(), Toast.LENGTH_LONG).show()

            bundle.putString("nameofitem", itemorder!!.get(po))
           startActivity(
               Intent(
                   this, PictareOfFolder::class.java


               ).putExtras(bundle)
           )
        }
    }

    override fun getPostionSelected(vew: View) {

        if(vew.id==R.id.pup){
            greatPop(vew)
        }
    }
    fun greatPop(v: View){
        var pop = PopupMenu(this, v)
        pop.inflate(R.menu.itemtopdf)
        pop.setOnMenuItemClickListener(this)
        pop.show()
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        when (p0!!.getItemId()) {

        }
        return false
    }


}

