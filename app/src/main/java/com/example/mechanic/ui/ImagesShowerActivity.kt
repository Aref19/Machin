package com.example.mechanic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.Adpter.RecyclerAdpterForTableandAll
import com.example.mechanic.Interfacess.ConverttoPdf
import com.example.mechanic.Interfacess.RcyclerPosition
import com.example.mechanic.R
import com.example.mechanic.ViewModels.ConvertedPDfViewModle
import com.example.mechanic.ViewModels.ImageLoadViewModel
import com.example.mechanic.saveData.room.Item
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

class ImagesShowerActivity : AppCompatActivity(), RcyclerPosition,PopupMenu.OnMenuItemClickListener,ConverttoPdf {
    var imageviewshower:ImageView?=null
    var recyl: RecyclerView? = null
    var textpop:TextView?=null
    var name:TextView?=null
    var imageLoadViewModel: ImageLoadViewModel? = null
    var covertimage:ConvertedPDfViewModle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.images_shower)
        imageviewshower=findViewById(R.id.imageViewshower)
        textpop=findViewById(R.id.textpop)
        covertimage= ConvertedPDfViewModle(this)
        imageLoadViewModel= ImageLoadViewModel(application)
        name=findViewById(R.id.pdfimage)
        name!!.text= getnameFromSelectedItem()
        GlobalScope.launch(Dispatchers.Main) {
        Picasso.get().load(File(   imageLoadViewModel!!.itemFromDataBase(getnameFromSelectedItem()).file))
            .transform(RoundedCornersTransformation(10, 10)).resize(1000,1000).into(imageviewshower)}
        textpop!!.setOnClickListener {

            var pop = PopupMenu(this, textpop)
            pop.inflate(R.menu.itemtopdf)
            pop.setOnMenuItemClickListener(this)
            pop.show()
        }
        GlobalScope.launch(Dispatchers.Main) {
        //    covertimage!!.converImaheToPdf("Foliate/"+getnameFromSelectedItem()+".jpg".trim().toString(),getnameFromSelectedItem())

            covertimage!!.convertpdf2(getnameFromSelectedItem())
        }


    }


    private fun getnameFromSelectedItem(): String{
        return intent.extras!!.getString("nameofitem").toString()


    }

    override fun getPostionSelected(po: Int, from: String) {
        TODO("Not yet implemented")
    }

    override fun getPostionSelected(vew: View) {
        TODO("Not yet implemented")
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        TODO("Not yet implemented")
    }

    override fun convertTopdf(result: Boolean) {
       if(result){
           Toast.makeText(this,"Image Converted ",Toast.LENGTH_LONG).show()
       }else
           Toast.makeText(this,"wrong  ",Toast.LENGTH_LONG).show()

    }


}