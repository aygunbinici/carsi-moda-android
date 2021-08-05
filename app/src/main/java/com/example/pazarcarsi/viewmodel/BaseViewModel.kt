package com.example.pazarcarsi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext

        get()  = job + Dispatchers.Main // işini yap(arka planda) ondan sonra maine dön anlamına geliyor

    override fun onCleared() {  //oncleared eğer bişey destroy olursa app kapatılırsa cansel--> bu iş iptal et
        super.onCleared()
        job.cancel()
    }
}