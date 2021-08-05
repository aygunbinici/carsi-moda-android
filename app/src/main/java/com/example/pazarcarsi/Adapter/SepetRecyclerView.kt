package com.example.pazarcarsi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.pazarcarsi.R
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.model.SepetModel
import com.example.pazarcarsi.model.Urun
import kotlinx.android.synthetic.main.item_row_sepet.view.*

class SepetRecyclerView(val basketlist:List<SepetModel>,val spinspin:ArrayAdapter<String>):RecyclerView.Adapter<SepetRecyclerView.ViewHolder>() {

    class ViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_sepet,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return basketlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.view.spinner.adapter=spinspin
        holder.view.spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {

                if (pos==0){

                  }

                if (pos==1){
                    val updatesayi=pos
                    basketlist[position].sayi=updatesayi
                }
                if (pos==2){
                    basketlist[position].sayi=pos

                }
                if (position==3){
                    basketlist[position].sayi=position

                }
                if (position==4){

                }
                if (position==5){

                }
                if (position==6){

                }
                if (position==7){

                }
                if (position==8){

                }
                if (position==9){

                }
                if (position==10){

                }


            }

        }
            
            val adet = basketlist[position].sayi.toDouble()
            val fiyat =basketlist[position].kiyafetfiyat.split(" ")[0].toDouble()
            val number2digits: Double = Math.round(fiyat*100.0)/ 100.0
            val sonuc=adet*number2digits
            val number3digits: Double = Math.round(sonuc*100.0)/ 100.0


            println("vvv adet " + adet)
            println("vvv number2digits " + number2digits)
            println("vvv fiyat " + fiyat)

            holder.view.sepetimageView.setImageResource(basketlist[position].kiyafetfoto)
            holder.view.sepetfiyat.text="ürün fiyatı "+basketlist[position].kiyafetfiyat
            holder.view.sepeturunisim.text=basketlist[position].kiyafetisim
            holder.view.sepetcount.text ="seçilen ürün adedi "+basketlist[position].sayi.toString()
            holder.view.maliyet.text="Ürün toplam maliyeti ${number3digits} TL "

    }

}