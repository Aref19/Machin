package com.example.mechanic.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.mechanic.Interfacess.FirebaseAcount
import com.example.mechanic.R
import com.example.mechanic.ViewModels.RegistierenViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable
import kotlinx.android.synthetic.main.activity_registeren.*

class Registeren : AppCompatActivity(),FirebaseAcount {
    var name: TextInputLayout? = null
    var emai: TextInputLayout? = null
    var endna: TextInputLayout? = null
    var pas: TextInputLayout? = null
    var pasw: TextInputLayout? = null
    var registierenViewModel:RegistierenViewModel?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeren)
        name = findViewById(R.id.names)
        endna = findViewById(R.id.endname)
        pas = findViewById(R.id.pass)
        pasw = findViewById(R.id.passwider)
        emai = findViewById(R.id.email)
        registierenViewModel= RegistierenViewModel(this)
        Log.i("name", "onCreate: " + name + emai + endna + pas)
        val fab = findViewById<FloatingActionButtonExpandable>(R.id.fab)
        fab.collapse()
        fab.isClickable=false
        pasw!!.editText?.doAfterTextChanged { text ->
            if (checkIfallFull(
                    arrayListOf<EditText>(
                        name!!.editText!!,
                        emai!!.editText!!,
                        endna!!.editText!!,
                        pas!!.editText!!,
                        pasw!!.editText!!
                    )
                )
            ) {
                if (checkpass(
                        pas!!.editText!!.text.toString(),
                        pasw!!.editText!!.text.toString()
                    )
                ) {
                    fab.expand()
                    fab.isClickable=true
                }


            }

        }

        fab.setOnClickListener {
            registierenViewModel!!.rigistierenwith(emai!!.editText?.text.toString(),pasw!!.editText?.text.toString())
        }


    }

    fun checkpass(pass1: String, pass2: String): Boolean {
        return pass1.equals(pass2)
    }

    fun checkIfallFull(arrrofText: ArrayList<EditText>): Boolean {

        for (e in 0 until (arrrofText.size))
            if (arrrofText.get(e).text.toString().equals("")) {
                arrrofText.get(e).error = "Erorr"
                Log.i("sizeee", "checkIfallFull: " + e + "" + arrrofText.size)
                if (e == arrrofText.size) {
                    return false
                }


            }

        return true
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun rgeistieren(status: Boolean) {
             if(status){
                 Toast.makeText(this,R.string.suc,Toast.LENGTH_LONG).show()
             }else
                 Toast.makeText(this,R.string.er,Toast.LENGTH_LONG).show()

    }

}



