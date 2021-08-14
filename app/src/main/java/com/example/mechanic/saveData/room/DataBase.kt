package com.example.mechanic.saveData.room

import android.content.Context
import androidx.room.*
@Database(entities =  arrayOf(Item::class
),version = 1)


abstract class DataBase : RoomDatabase() {

    abstract fun dao(): DaoItemsImages

    companion object{
          @Volatile
          private  var Instas:DataBase?=null
        fun  getDatabase(con:Context):DataBase=
            Instas?: synchronized(this) {
                Instas?:
                bulidDataBase(con).also { Instas=it }
            }



      private  fun bulidDataBase(con:Context)=
            Room.databaseBuilder(
               con,DataBase::class.java,"Images"
            ).allowMainThreadQueries().build()








        }


    }


