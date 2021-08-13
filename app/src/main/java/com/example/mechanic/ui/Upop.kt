package com.example.mechanic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.mechanic.R

class Upop : DialogFragment, View.OnClickListener {
    var vie: View? = null
    var button: Button? = null
    var text:EditText?=null
    var name:String?=null

    constructor() {


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vie = inflater.inflate(R.layout.upop, container, false)
        button = vie!!.findViewById(R.id.nameselect)
        text= vie!!.findViewById(R.id.nameimgafile)
        button!!.setOnClickListener(this)
        return vie
    }

    override fun onClick(p0: View?) {
        name=text!!.text.toString()
       getnameFromuser()
        this.dismiss()
    }

     fun getnameFromuser():String =
       name!!


}