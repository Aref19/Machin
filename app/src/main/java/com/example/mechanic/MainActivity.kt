package com.example.mechanic

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
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

        Log.i("aref", "onCreate: "+"dasdsa")

          GlobalScope.launch(Dispatchers.Default) {

              longForImage()


          }







}
    suspend fun longForImage(){
        delay( 3000L)
        if(fire!!.uid==null){
            Log.i("aref", "onCreate: "+"dasds2a")
            startActivity(Intent(this,Registeren::class.java))
        }else
            startActivity(Intent(this,Login::class.java))
        Log.i("aref", "onCreate: "+"dasds3a")
    }



}