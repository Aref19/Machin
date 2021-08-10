package com.example.mechanic.ViewModels

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mechanic.Interfacess.FirebaseAcount
import com.google.firebase.auth.FirebaseAuth

class RegistierenViewModel :ViewModel {
     var firebaseAcount:FirebaseAcount?=null
    var firAuth:FirebaseAuth= FirebaseAuth.getInstance()
    constructor(con:FirebaseAcount){
        this.firebaseAcount= con
    }
    fun rigistierenwith(email:String,pass:String){
        firAuth.createUserWithEmailAndPassword(email.trim(),pass).addOnCompleteListener {  it->
            if(it.isSuccessful){
                firebaseAcount!!.rgeistieren(true)
            }else
                firebaseAcount!!.rgeistieren(false)
            Log.i("exp", "rigistierenwith: "+it.exception)

        }


    }



}