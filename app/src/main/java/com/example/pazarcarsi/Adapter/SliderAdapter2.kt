package com.example.pazarcarsi.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.pazarcarsi.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter2(mImages:ArrayList<Int>):SliderViewAdapter<SliderAdapter2.ViewHolder>() {

    var images=mImages


    class ViewHolder(view:View):SliderViewAdapter.ViewHolder(view) {
        var imageView: ImageView

        init {
            imageView=view.findViewById(R.id.image_view2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapter2.ViewHolder {
        val inflate: View = LayoutInflater.from(parent?.context).inflate(R.layout.itemslider2,null)

        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapter2.ViewHolder?, position: Int) {
        viewHolder?.imageView!!.setImageResource(images.get(position))
    }

    override fun getCount(): Int {
        return images.size
    }
}