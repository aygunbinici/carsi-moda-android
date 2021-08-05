package com.example.pazarcarsi.service2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.model.SepetModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SepetViewModel(application: Application):AndroidViewModel(application) {

    val urunDao= SepetDataBase.getDatabase(application).urundao()
    val basket=MutableLiveData<List<Sepet>>()
    val totalBasket = MutableLiveData<Double>()
    val kyff= MutableLiveData<SepetModel>()

    var readAllData : LiveData<List<Sepet>> = urunDao.readAllData()

    val whit_id = MutableLiveData<List<Sepet>>()
    val whit_id2 = MutableLiveData<List<SepetModel>>()



//    fun kyfkyfkyf(kyf:String){
//        viewModelScope.launch {
//            val abc=urunDao.kyfkyf(kyf)
//            kyff.value=abc
//
//        }
//    }

    fun addsepet(sepet: Sepet){
        viewModelScope.launch(Dispatchers.IO) {
            urunDao.addsepete(sepet)
        }
    }

    fun get_with_id2(id:Int){
        viewModelScope.launch {
            val abc=urunDao.getkyf(id)
            whit_id2.value=abc
          }
     }


//    fun get_with_id(id:Int){
//        viewModelScope.launch {
//        val abc=urunDao.getid(id)
//            whit_id.value=abc
//        }
//    }

      fun refreshTotalValue(listOfProduct:List<SepetModel>){
        var total:Double= 0.0
        listOfProduct.forEach {product->
            val price=product.kiyafetfiyat
            val sonuc=price.split(" ")
            val sonuc2=sonuc[0].toDouble()
 
            sonuc2.let {
                val count=product.sayi
                val revenue= count * it
                total+=revenue
            }
        }
        totalBasket.value=total
    }

     fun addtoBasket(sepet: Sepet){

         if (basket.value!=null){
            val arraylist = ArrayList(basket.value)

            if (arraylist.contains(sepet)){
                val indexOfFirst = arraylist.indexOfFirst {
                    it==sepet
                }

                val relatedProduct=arraylist.get(indexOfFirst)
                relatedProduct.count+=1
                basket.value=arraylist
            }
            else{
                sepet.count +=1
                arraylist.add(sepet)
                basket.value=arraylist
            }

        }
        else{
            val arrayList= arrayListOf(sepet)
            sepet.count+=1
            basket.value=arrayList
        }




    }

}