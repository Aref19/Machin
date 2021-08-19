package com.example.mechanic.saveData.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    @Query("select * from images where id = :id")
    fun  selectitem(id:String):Item
    @Query("select 1 from images where id = :id")
    fun  checkIfItem(id:String):Boolean
}