package com.example.pazarcarsi.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.pazarcarsi.Adapter.SliderAdapter
import com.example.pazarcarsi.R
import com.example.pazarcarsi.Util.CustomSharedPreferences
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.model.Urun
import com.example.pazarcarsi.service2.SepetViewModel
import com.example.pazarcarsi.viewmodel.HomeViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_urun.*
import java.lang.String.format


class UrunFragment() : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var sepetViewModel: SepetViewModel
    lateinit var sharedPref: CustomSharedPreferences
    lateinit var buttondenemesi:Button

    var gelen_id_shared=0


    lateinit var sliderView:SliderView

     private var gelenkiyafet=""
    private var geleneski=""
    private var gelenyeni=""
    private var gelenfoto=0

    private var yuzde:Float = 0.0F
    private var yuzdee:Float = 0.0F

    lateinit var aygunn:NestedScrollView

    var images: ArrayList<Int> = arrayListOf(R.drawable.ptln1, R.drawable.ptln2, R.drawable.ptln3,R.drawable.ptln4)
    lateinit var buton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_urun, container, false)

        buttondenemesi=root.findViewById(R.id.denemebutonu)

        val ok = root.findViewById<TextView>(R.id.ok)
        val deneme=root.findViewById<ConstraintLayout>(R.id.consdenemebuton)
        val deneme2=root.findViewById<ConstraintLayout>(R.id.consdeneme2)
        sliderView = root.findViewById<SliderView>(R.id.image_slider2)

        aygunn=root.findViewById<NestedScrollView>(R.id.aygun)
        buton=root.findViewById(R.id.sepeteekle)
        sharedPref= CustomSharedPreferences()

        buttondenemesi.setOnClickListener {
            sepetViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                println(it)
            })
        }


        ok.setOnClickListener {
            val action=UrunFragmentDirections.actionUrunFragmentToHome()
            Navigation.findNavController(it).navigate(action)
        }

        deneme.setOnClickListener {
            val action=UrunFragmentDirections.actionUrunFragmentToAciklamaFragment()
            Navigation.findNavController(it).navigate(action)
           }

        deneme2.setOnClickListener {
            val action=UrunFragmentDirections.actionUrunFragmentToYorumFragment()
            Navigation.findNavController(it).navigate(action)
         }

        val sliderView = root.findViewById<SliderView>(R.id.image_slider2)
//        val sliderAdapter= SliderAdapter(images)
//        sliderView.setSliderAdapter(sliderAdapter)
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM)
//        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
//        sliderView.startAutoCycle()

         return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        sepetViewModel=ViewModelProviders.of(this).get(SepetViewModel::class.java)
        viewModel.refresh2()

        gelen_id_shared= sharedPref.getid()!!


//        aygunn.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
//
//        }

        arguments?.let {
            gelenkiyafet=UrunFragmentArgs.fromBundle(it).kiyafetisim
            geleneski=UrunFragmentArgs.fromBundle(it).eskifiyat
            gelenyeni=UrunFragmentArgs.fromBundle(it).yenifiyat
            gelenfoto=UrunFragmentArgs.fromBundle(it).urunfoto
        }

        buton.setOnClickListener {

            Toast.makeText(requireContext(),"${gelenkiyafet} Sepete Eklendi",Toast.LENGTH_SHORT).show()
            val sepet=Sepet(0,gelen_id_shared,gelenkiyafet,gelenyeni,gelenfoto,0)
            sepetViewModel.addsepet(sepet)

         }

        val sonuc=geleneski.split(" ")
        println("geleneski"+geleneski)
        val sonuc2=sonuc[0].toFloat()
        println("sonuc2 "+sonuc2)

        val sonuc3=gelenyeni.split(" ")
        val sonuc4=sonuc3[0].toString().toFloat()
        println("sonuc4 "+sonuc4)

        kiyafetisim.text=gelenkiyafet
        textfiyateski.text=geleneski.toString()
        textfiyat.text=gelenyeni.toString()

        var fark:Float=sonuc2-sonuc4
        println(fark)
        yuzde= ((100*fark)/sonuc2).toFloat()

        val number2digits: Double = Math.round(yuzde*100.0)/ 100.0

        indirim.text="("+"%"+number2digits.toString()+" indirim"+")"
        text1.text=gelenkiyafet

        observeLiveData2()

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


    interface Listener{
        fun onItemClick(urun: Urun)
    }



}
