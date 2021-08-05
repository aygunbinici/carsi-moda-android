package com.example.pazarcarsi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pazarcarsi.Adapter.RecyclerViewAdapter
import com.example.pazarcarsi.Adapter.RecyclerViewAdapter2
import com.example.pazarcarsi.Adapter.RecyclerViewAdapter3
import com.example.pazarcarsi.Adapter.SliderAdapter
import com.example.pazarcarsi.R
import com.example.pazarcarsi.viewmodel.HomeViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

     private lateinit var viewModel:HomeViewModel
     private val recyclerViewAdapter=RecyclerViewAdapter(arrayListOf())
     private val recyclerViewAdapter2=RecyclerViewAdapter2(arrayListOf())
     private val recyclerViewAdapter3= RecyclerViewAdapter3(arrayListOf())

    lateinit var sliderView:SliderView
    lateinit var nested:NestedScrollView

//     var images: ArrayList<Int> = arrayListOf(R.drawable.ptln1, R.drawable.ptln2, R.drawable.ptln3,R.drawable.ptln4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_home, container, false)

        sliderView = root.findViewById<SliderView>(R.id.image_slider)
        nested=root.findViewById(R.id.nested)

//        val sliderAdapter=SliderAdapter(images)
//        sliderView.setSliderAdapter(sliderAdapter)
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
//        sliderView.startAutoCycle()

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        nested.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
//
//               if (scrollY>oldScrollY){ //aşağı doğru gidiyorsa
//                }
//               else if (oldScrollY>scrollY){//yukarı doğru gidiyorsa
//               }
// //                println("YYY " + scrollY)
////                println("YYYeski " + oldScrollY)
////                println("yeniX"+scrollX)
////                println("eskiX"+oldScrollX)
//// //            nested.scrollTo(0,nested.bottom)
//// //            nested.scrollTo(0,nested.top)
//
//        }



        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refresh()
        viewModel.refresh2()

        recyclerid1.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerid1.adapter=recyclerViewAdapter
        recyclerid2.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerid2.adapter=recyclerViewAdapter2
        recyclerid3.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerid3.adapter=recyclerViewAdapter3

        observeLiveData()
        observeLiveData2()

    }

    private fun observeLiveData(){
        viewModel.urunler.observe(viewLifecycleOwner, Observer { haber->

            haber?.let {

                println("aygün"+haber)

                recyclerid1.visibility=View.VISIBLE
                recyclerid2.visibility=View.VISIBLE
                recyclerid3.visibility=View.VISIBLE

                recyclerViewAdapter.updateCountyList(haber)
                recyclerViewAdapter2.updateCountyList(haber)
                recyclerViewAdapter3.updateCountyList(haber)

             }
        })

    }

    private fun observeLiveData2(){
        viewModel.resimler.observe(viewLifecycleOwner, Observer {resim ->
            resim.let {
                val sliderAdapter=SliderAdapter(resim)
                sliderView.setSliderAdapter(sliderAdapter)
                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
                sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
                sliderView.startAutoCycle()
            }
        })
    }



 }