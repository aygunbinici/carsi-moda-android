package com.example.pazarcarsi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pazarcarsi.model.User

@Database(entities = arrayOf(User::class),version = 1)
abstract class UserDataBase:RoomDatabase(){
    abstract fun userDao():userDao


    companion object{
        private var INSTANCE:UserDataBase?=null

        fun getDatabase(context: Context):UserDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, UserDataBase::class.java, "user_database").build()
                INSTANCE=instance
                return instance
            }
        }

    }


}