package com.example.pazarcarsi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sepet_table")
data class Sepet(

        @PrimaryKey(autoGenerate = true)
        val id:Int,

        val kullanici_id:Int,
        val kiyafetisim:String,
        val kiyafetfiyat:String,
        val kiyafetfoto:Int,
        var count:Int

)