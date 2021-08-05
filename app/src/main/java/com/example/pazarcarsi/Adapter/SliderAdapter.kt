package com.example.pazarcarsi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pazarcarsi.R
import com.example.pazarcarsi.model.Resim
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(mImages: ArrayList<Resim>):SliderViewAdapter<SliderAdapter.ViewHolder>() {

    var images=mImages


    class ViewHolder(view:View):SliderViewAdapter.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView=view.findViewById(R.id.image_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter.ViewHolder {
        val inflate: View = LayoutInflater.from(parent?.context).inflate(R.layout.itemslider,null)

        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapter.ViewHolder?, position: Int) {
        viewHolder?.imageView!!.setImageResource(images[position].sliderfoto)
    }

    override fun getCount(): Int {
        return images.size
    }
}