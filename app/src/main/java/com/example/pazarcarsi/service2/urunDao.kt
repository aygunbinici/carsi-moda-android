package com.example.pazarcarsi.service2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.model.SepetModel
import com.example.pazarcarsi.model.User


@Dao
interface urunDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addsepete(vararg sepet: Sepet)

    @Query("SELECT * FROM sepet_table WHERE kullanici_id=:alinanid ")
    suspend fun getid(alinanid:Int):List<Sepet>

    @Query("SELECT * FROM sepet_table")
    fun readAllData(): LiveData<List<Sepet>>

//    @Query("SELECT * FROM sepet_table WHERE kiyafetisim=:isimkyf")
//    fun kyfkyf(isimkyf:String):SepetModel

    @Query("SELECT COUNT(kiyafetisim) as sayi,* FROM sepet_table WHERE kullanici_id=:custermerid GROUP BY kiyafetisim ")
    suspend fun getkyf(custermerid:Int):List<SepetModel>


}