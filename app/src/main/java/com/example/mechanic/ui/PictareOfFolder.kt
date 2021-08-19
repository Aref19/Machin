package com.example.mechanic.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Adpter.RecyclerAdpterForTableandAll
import com.example.mechanic.Interfacess.RcyclerPosition
import com.example.mechanic.R
import com.example.mechanic.ViewModels.ImageLoadViewModel
import com.example.mechanic.saveData.room.Item

class PictareOfFolder : AppCompatActivity(), RcyclerPosition{
    var listofimages = ArrayList<Item>()
    var recyl: RecyclerView? = null
    var imageLoadViewModel: ImageLoadViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictare_of_folder)

        recyl = findViewById(R.id.recycleroftable)
        recyl!!.layoutManager = LinearLayoutManager(applicationContext)
        imageLoadViewModel = ImageLoadViewModel(application)
        var ad = RecyclerAdpterForTableandAll(listofimages, this)

        listofimages.clear()

        recyl!!.adapter = ad


    }


    override fun getPostionSelected(po: Int, from: String) {

    }

    override fun getPostionSelected(vew: View) {

        if (vew.id == R.id.pup) {
            greatPop(vew)
        }
    }

    fun greatPop(v: View) {
        var pop = PopupMenu(this, v)
        pop.inflate(R.menu.itemtopdf)

        pop.show()
    }



    private fun getnameFromSelectedItem(): String{


        return intent.extras!!.getString("nameofitem").toString()


    }
}