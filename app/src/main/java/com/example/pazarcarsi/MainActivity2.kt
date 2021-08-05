package com.example.pazarcarsi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.pazarcarsi.Util.CustomSharedPreferences

class MainActivity2 : AppCompatActivity() {

    lateinit var sharedPref: CustomSharedPreferences

    lateinit var handler: Handler


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        sharedPref= CustomSharedPreferences(applicationContext)

//        sharedPref.kaydet(52)


        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR


        handler= Handler()
        handler.postDelayed({

            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        },3000)



    }
}