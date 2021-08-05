package com.example.pazarcarsi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.pazarcarsi.R
import com.example.pazarcarsi.model.Urun
import com.example.pazarcarsi.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(val urun:ArrayList<Urun>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(var view:View):RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_row,parent,false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return urun.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val kiyafet=urun[position].urunismi
        val eskifiyat:String=urun[position].uruneskifiyat
        val yenifiyat:String=urun[position].urunfiyat
        val foto:Int=urun[position].urunfoto

        holder.view.urun.text=kiyafet
        holder.view.fiyat.text=yenifiyat.toString()
        holder.view.urunfoto.setImageResource(urun[position].urunfoto)


        holder.view.setOnClickListener {

             val action=HomeFragmentDirections.actionHomeToUrunFragment2(kiyafet,eskifiyat,yenifiyat,foto)
             Navigation.findNavController(it).navigate(action)

          }

    }

    fun updateCountyList(newCountryList:List<Urun>){
        urun.clear()
        urun.addAll(newCountryList)
        notifyDataSetChanged() //adaptörü yenilemek için kullandığımız metod
    }
}