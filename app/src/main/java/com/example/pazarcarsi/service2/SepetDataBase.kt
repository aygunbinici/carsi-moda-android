package com.example.pazarcarsi.service2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.service.UserDataBase


@Database(entities = arrayOf(Sepet::class),version = 1)
abstract class SepetDataBase: RoomDatabase() {

    abstract fun urundao():urunDao

    companion object{
        private var INSTANCE: SepetDataBase?=null

        fun getDatabase(context: Context):SepetDataBase{
            val tempInstance = SepetDataBase.INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, SepetDataBase::class.java, "sepet_database").build()
                SepetDataBase.INSTANCE =instance
                return instance
            }
        }
    }



}