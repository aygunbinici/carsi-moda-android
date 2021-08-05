package com.example.pazarcarsi.model

import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey

 data class Urun (

    val urunismi:String,
    val urunfoto:Int,
    val urunfiyat:String,
    val uruneskifiyat:String


)

{
    var count=0
}