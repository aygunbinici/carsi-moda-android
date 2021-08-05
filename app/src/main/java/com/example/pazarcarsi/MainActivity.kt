package com.example.pazarcarsi

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.pazarcarsi.Util.CustomSharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


     private lateinit var bottomNavigationView: BottomNavigationView
     private lateinit var navController: NavController
     lateinit var sharedPref: CustomSharedPreferences


      @RequiresApi(Build.VERSION_CODES.M)
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref= CustomSharedPreferences(applicationContext)

         val printid= sharedPref.getid()

          println("abcde "+printid)

        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR



        bottomNavigationView=findViewById(R.id.bottomnav)
          navController= Navigation.findNavController(this,R.id.fragment)

         NavigationUI.setupWithNavController(bottomNavigationView,navController)


    }



}