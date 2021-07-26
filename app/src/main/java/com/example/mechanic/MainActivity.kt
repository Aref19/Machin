package com.example.mechanic

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mechanic.ui.Login
import com.example.mechanic.ui.Registeren
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity()  {
    var fire:FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
          fire= FirebaseAuth.getInstance()
          if(fire!!.uid==null){
              startActivity(Intent(this,Registeren::class.java))
          }else
              startActivity(Intent(this,Login::class.java))



}



}