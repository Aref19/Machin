package com.example.mechanic.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.mechanic.Interfacess.FirebaseAcount
import com.example.mechanic.R
import com.example.mechanic.saveData.MangeCache
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.tuann.floatingactionbuttonexpandable.FloatingActionButtonExpandable

class Login : AppCompatActivity(), FirebaseAcount {
    var emai: TextInputLayout? = null
    var firba: FirebaseAuth? = null
    var pass: TextInputLayout? = null
    var textpass:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pass = findViewById(R.id.pass)
        emai = findViewById(R.id.emailfromlog)
        textpass=findViewById(R.id.passforget)
        val fab1 = findViewById<FloatingActionButtonExpandable>(R.id.anmeldenlog)
        val fab2 = findViewById<FloatingActionButtonExpandable>(R.id.registieren)
        firba=FirebaseAuth.getInstance()
        if (MangeCache(this)!!.checkIfRegisterd()) {
            Log.i("sta", "onCreate: " + "sucsess")
        } else
            fab1.isClickable=true
            fab1.setOnClickListener {
                Toast.makeText(this, "check your input", Toast.LENGTH_LONG).show()
                if (!emai!!.editText?.text.toString()
                        .equals("") && !pass!!.editText?.text.toString().equals("")
                ) {
                    MangeCache(this).savePassAndEmail(
                         emai!!.editText?.text.toString(),
                        pass!!.editText?.text.toString()
                    )
                    login()
                } else
                    Toast.makeText(this, "check your input", Toast.LENGTH_LONG).show()


            }
        fab2.setOnClickListener {
            startActivity(Intent(this,Registeren::class.java))
        }
        textpass!!.setOnClickListener {
            passForget()
        }


    }

    override fun login() {

        firba!!.signInWithEmailAndPassword(
            emai!!.editText?.text.toString(),
            pass!!.editText?.text.toString()
        ).addOnCompleteListener { it ->

            if (it.isSuccessful) {
                Log.i("sta", "onCreate: " + "sucsess2")
            } else
                Toast.makeText(this, "check your input", Toast.LENGTH_LONG).show()


        }
    }

    override fun rgeistieren(status: Boolean) {
        TODO("Not yet implemented")
    }

    fun passForget() {

        if(!emai!!.editText?.text.toString().equals("")){
            firba!!.sendPasswordResetEmail( emai!!.editText?.text.toString().trim()).addOnCompleteListener {it->
                if(it.isSuccessful){
                    Toast.makeText(getApplication(), "please check your Email", Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(this, "check your input"+it.exception, Toast.LENGTH_LONG).show()

            }


        }else
            Toast.makeText(this, "check your input1", Toast.LENGTH_LONG).show()
    }
}