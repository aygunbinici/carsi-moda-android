package com.example.pazarcarsi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.example.pazarcarsi.Util.CustomSharedPreferences
 import com.example.pazarcarsi.model.User
import com.example.pazarcarsi.service.UserViewModel
 import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    lateinit var sharedPref: CustomSharedPreferences

    var firstName:String=""
    var lastName:String=""
    lateinit var firstedit: EditText
    lateinit var secondedit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        firstedit=findViewById(R.id.kullanıcıadı)
        secondedit=findViewById(R.id.şifre)

        sharedPref= CustomSharedPreferences(applicationContext)

        kayıt.setOnClickListener {

            insertDataToDatabase()
            firstedit.setText("")
            secondedit.setText("")

        }

        giris.setOnClickListener {
            giriskontrol()
//            mUserViewModel.Alldelete()
        }

         mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
         mUserViewModel.countryLiveData.observe(this, Observer {
            if(it!= null){
                Toast.makeText(this,"kullanıcı ismi mevcut", Toast.LENGTH_SHORT).show()
            }
            else{
                val user= User(0,firstName,lastName)
                mUserViewModel.addUser(user)
                Toast.makeText(this,"kayıt başarılı", Toast.LENGTH_SHORT).show()
            }
        })
        //****************************************************************************************************************

        mUserViewModel.kullanicigirisi.observe(this, Observer {
            if (it!=null){
                Toast.makeText(this,"giriş başarılı", Toast.LENGTH_SHORT).show()
                val id=it.id
                sharedPref.kaydet(id)
                val intent=Intent(this,MainActivity2::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"giriş başarısız", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun insertDataToDatabase() {
        firstName =firstedit.text.toString()
        lastName = secondedit.text.toString()

        if (firstName!="" && lastName!=""){
            mUserViewModel.controluser(firstName)
        }
        else
        {
            Toast.makeText(this,"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun giriskontrol(){
        firstName =firstedit.text.toString()
        lastName = secondedit.text.toString()

        if (firstName!="" && lastName!=""){
            mUserViewModel.controlgirisuser(firstName,lastName)
        }
        else
        {
            Toast.makeText(this,"Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }
}

