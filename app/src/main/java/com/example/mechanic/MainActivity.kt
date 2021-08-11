package com.example.mechanic

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.mechanic.saveData.MangeCache
import com.example.mechanic.ui.CentralActivty
import com.example.mechanic.ui.Login
import com.example.mechanic.ui.Registeren
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity()  {
    var fire:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          fire= FirebaseAuth.getInstance()



          GlobalScope.launch(Dispatchers.Default) {

              longForImage()


          }







}
    suspend fun longForImage(){
        delay( 1000L)
     if(MangeCache(this).checkIfRegisterd()){
         startActivity(Intent(this,CentralActivty::class.java))
         this.finish()
         Log.i("aref", "onCreate: "+"dasds3a")
     }else
         startActivity(Intent(this,Login::class.java))


    }



}