package com.example.mechanic.saveData.room

import android.graphics.Bitmap
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "images")
class Item (
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var id : String,
    var file: String
)


