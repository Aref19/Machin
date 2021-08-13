package com.example.mechanic.saveData.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoItemsImages {
    @Query("select * from images ")
    fun getAll(): LiveData<List<Item>>

    @Insert
   suspend fun insertAll(item: Item)

    @Query("DELETE FROM images")
    fun delete()
}