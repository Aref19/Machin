package com.example.mechanic.Interfacess

import android.graphics.Bitmap
import android.net.Uri
import java.net.URI

interface ImageInterface {
    fun lodedImage(bit:Bitmap?,name:String)
    fun mkorder(nameorder:String)

}