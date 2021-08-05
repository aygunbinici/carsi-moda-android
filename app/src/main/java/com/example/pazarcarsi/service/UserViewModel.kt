package com.example.pazarcarsi.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pazarcarsi.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {
    val userDao=UserDataBase.getDatabase(application).userDao()

    val countryLiveData = MutableLiveData<User>()
    val kullanicigirisi = MutableLiveData<User>()
    val kullanici_whit_id = MutableLiveData<User>()


    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
        }
    }

    fun getuser(id: Int) {
        viewModelScope.launch {
            userDao.getUser(id)
            val abc=userDao.getUser(id)
            kullanici_whit_id.value=abc
        }
    }

    fun getAlluser() {
        viewModelScope.launch {
            userDao.readAllData()
        }
    }

    fun Alldelete() {
        viewModelScope.launch {
            userDao.deleteAllUsers()
        }
    }

    fun controluser(user:String){
        viewModelScope.launch {
            val abc=userDao.controluser(user)
            countryLiveData.value=abc
        }
    }

    fun controlgirisuser(user:String,parola:String){
        viewModelScope.launch {
            val abc=userDao.controlgiris(user,parola)
            kullanicigirisi.value=abc
        }
    }



//    fun controlgiris(kullanici:String,password:String) {
//        viewModelScope.launch {
//            userDao.controlgiris(kullanici,password)
//        }
//    }



}