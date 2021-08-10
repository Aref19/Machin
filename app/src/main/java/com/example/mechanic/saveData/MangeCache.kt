package com.example.mechanic.saveData

import android.content.Context

class MangeCache(con: Context)  {

        val SHARED_PREFS_KEY = "com.example.mechanic.shared_prefs"
        val email:String?="email_Key"
        val passkey:String?="pass_key"


    var con=con
    fun savePassAndEmail(emai:String,pass:String){
      val pr=  con.getSharedPreferences(SHARED_PREFS_KEY,Context.MODE_PRIVATE).edit()
        with(pr){
            putString(email,emai)
            putString(passkey,pass)
        }



    }
    fun checkIfRegisterd():Boolean{
        val prfernce=   con.getSharedPreferences(SHARED_PREFS_KEY,Context.MODE_PRIVATE)
         if(!!prfernce.getString(SHARED_PREFS_KEY,"").equals("")){
             return true
         }

        return  false
    }




}