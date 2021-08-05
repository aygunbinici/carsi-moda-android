package com.example.pazarcarsi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pazarcarsi.Adapter.SepetRecyclerView
import com.example.pazarcarsi.R
import com.example.pazarcarsi.Util.CustomSharedPreferences
import com.example.pazarcarsi.model.Sepet
import com.example.pazarcarsi.service.UserViewModel
import com.example.pazarcarsi.service2.SepetViewModel
import com.example.pazarcarsi.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_sepetim.*

class SepetimFragment : Fragment() {

    private  var sepetRecyclerView: SepetRecyclerView?=null
    lateinit var sepetUserViewModel: SepetViewModel
    lateinit var sharedPref: CustomSharedPreferences

    val sayilar= ArrayList<String>()
    lateinit var aygun: ArrayAdapter<String>

    lateinit var butonodeme:Button

    lateinit var sepetbos:ConstraintLayout
    lateinit var sepetdolu:ConstraintLayout
    lateinit var total:TextView

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        sayilar.add("Adet Seçiniz")
        sayilar.add("1")
        sayilar.add("2")
        sayilar.add("3")
        sayilar.add("4")
        sayilar.add("5")
        sayilar.add("6")
        sayilar.add("7")
        sayilar.add("8")
        sayilar.add("9")
        sayilar.add("10")

        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_sepetim, container, false)

        aygun= ArrayAdapter(root.context,R.layout.textview_blue,sayilar)


        sharedPref= CustomSharedPreferences()
        sepetbos=root.findViewById(R.id.sepetbos)
        sepetdolu=root.findViewById(R.id.sepetdolu)
        total=root.findViewById(R.id.totalfiyat)
        butonodeme=root.findViewById(R.id.ödeme)

        sepetdolu.isVisible=false
        sepetbos.isVisible=true

        butonodeme.setOnClickListener {
            val action=SepetimFragmentDirections.actionSepetToOdemeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sepetrecyclerdolu.layoutManager= LinearLayoutManager(activity?.baseContext)

        val shared_id=sharedPref.getid()
        sepetUserViewModel = ViewModelProviders.of(this).get(SepetViewModel::class.java)

        if (shared_id != null) {
            sepetUserViewModel.get_with_id2(shared_id)
        }

        sepetUserViewModel.whit_id2.observe(viewLifecycleOwner, Observer {
             println( it )
             if (it.size>0)
            {
                sepetUserViewModel.refreshTotalValue(it)
                sepetdolu.isVisible=true
                sepetbos.isVisible=false
                sepetRecyclerView=SepetRecyclerView(it,aygun)
                sepetrecyclerdolu.adapter=sepetRecyclerView

            }

            else
            {
                sepetdolu.isVisible=false
                sepetbos.isVisible=true
            }

        })

        sepetUserViewModel.totalBasket.observe(viewLifecycleOwner, Observer {
            println("sepetsepet " +it)
            val number2digits: Double = Math.round(it*100.0)/ 100.0
            println("sepetsonuc " +number2digits)
            total.text="Toplam Sepet: ${number2digits} TL"

        })

    }

 }