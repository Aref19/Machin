package com.example.mechanic.saveData.room

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class ConvaertImage {
    @TypeConverter
    fun  fromBitmabtoBitArray(bitmap: Bitmap):ByteArray{
        val outputstream=ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputstream)
        return outputstream.toByteArray()
    }
    @TypeConverter
     fun toBitmab(byteArray: ByteArray):Bitmap=
          BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)


}