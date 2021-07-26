package com.example.mechanic.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.mechanic.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable
import kotlinx.android.synthetic.main.activity_registeren.*

class Registeren : AppCompatActivity() {
    var name :TextInputLayout? = null
    var emai:TextInputLayout ?= null
    var endna:TextInputLayout? = null
    var pas  :TextInputLayout? = null
    var pasw :TextInputLayout?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeren)
        name = findViewById(R.id.names)
        endna = findViewById(R.id.endname)
        pas = findViewById(R.id.pass)
        pasw = findViewById(R.id.passwider)
        emai=findViewById(R.id.email)
        Log.i("name", "onCreate: "+name+emai+endna+pas)
        val fab = findViewById<FloatingActionButtonExpandable>(R.id.fab)
        fab.collapse()
       fab.setOnClickListener {
           if ( checkIfallFull(arrayListOf<EditText>(name!!.editText!!
                   ,emai!!.editText!!
                   , endna!!.editText!!
                   , pas!!.editText!!
                   , pasw!!.editText!!))) {


               fab.expand()
           }
       }
       }

    fun checkIfallFull(arrrofText: ArrayList<EditText>): Boolean {

        for (e in 0 until  (arrrofText.size))
            if (arrrofText.get(e).text.toString().equals("")) {
                arrrofText.get(e).error="Erorr"
             if(e==arrrofText.size-1){
                 return false
             }




            }

        return true
    }

    }



