package com.example.pazarcarsi.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pazarcarsi.model.User

@Dao
interface userDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(vararg user: User)

    @Query("SELECT * FROM user_table")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE id=:userId ")
    suspend fun getUser(userId :Int):User

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table WHERE kullanici_adi=:kullanici")
    suspend fun controluser(kullanici :String):User

    @Query("SELECT * FROM user_table WHERE kullanici_adi=:kullanici and password=:password")
    suspend fun controlgiris(kullanici :String,password:String):User



}