package com.example.pazarcarsi.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.pazarcarsi.R
import com.example.pazarcarsi.model.Resim
import com.example.pazarcarsi.model.Urun
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {  //coroutine muhabbeti base

    val urunler=MutableLiveData<List<Urun>>()
    private val disposable = CompositeDisposable()// CALL YAPTIKCA BUNUN İÇİNE ATIYORUZ FRAGMENT FALAN DEĞİŞTİKCE HAFIZAYI TEMİZLER

    val resimler=MutableLiveData<ArrayList<Resim>>()

       fun  refresh(){

            val urun1=Urun("YAZILI NAKIŞLI TİŞÖRT SİYAH", R.drawable.aygunbinici,"22.99 TL","74.99 TL")
            val urun2=Urun("ATLETİCH BASKILI TİŞÖRT EKRU",R.drawable.resteg,"34.99 TL","69.99 TL")
            val urun3=Urun("KRILKIL KUMAŞ ŞERİTLİ SIRT ÇANTASI",R.drawable.acar,"40.99 TL","90.99 TL")
            val urun4=Urun("KARE YAKA PUDRA", R.drawable.aygunbinici,"19.99 TL","49.99 TL")
            val urun5=Urun("KAŞKORSE ELBİSE", R.drawable.resteg,"29.99 TL","59.99 TL")
            val urun6=Urun("TAŞLI BASKILI TİŞÖRT BEYAZ", R.drawable.acar,"34.99 TL","69.99 TL")

            val urunlist= arrayListOf<Urun>(urun1,urun2,urun3,urun4,urun5,urun6)

            urunler.value=urunlist

    }

    fun refresh2(){

        val resim1=Resim(R.drawable.ptln1)
        val resim2=Resim(R.drawable.ptln2)
        val resim3=Resim(R.drawable.ptln3)
        val resim4=Resim(R.drawable.ptln4)

        val resimlist= arrayListOf<Resim>(resim1,resim2,resim3,resim4)

        resimler.value=resimlist

    }

}


