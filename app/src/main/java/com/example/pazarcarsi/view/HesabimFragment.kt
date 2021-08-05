package com.example.pazarcarsi.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
 import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
  import com.example.pazarcarsi.MainActivity3
import com.example.pazarcarsi.R
import com.example.pazarcarsi.Util.CustomSharedPreferences
import com.example.pazarcarsi.service.UserViewModel

class HesabimFragment : Fragment() {

    lateinit var button:Button
    lateinit var button2:Button
    lateinit var butondelete:Button
    lateinit var tasarim22:ConstraintLayout
    lateinit var tasarim11:NestedScrollView
    lateinit var text:TextView
    lateinit var mUserViewModel:UserViewModel
    var aygun :Int = 0

    lateinit var sharedPref: CustomSharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_hesabim, container, false)

        butondelete=root.findViewById(R.id.buttondelete)
        tasarim11=root.findViewById(R.id.tasarim1)
        tasarim22=root.findViewById(R.id.tasarim2)
        text=root.findViewById(R.id.kullanici)



        tasarim11.isVisible=true
        tasarim22.isVisible=false


        sharedPref= CustomSharedPreferences(root.context)
        aygun= sharedPref.getid()!!
        println("aygunaygun "+aygun)

//        if (aygun != null){
//            tasarim11.isVisible=false
//            tasarim22.isVisible=true
//            println("aaaaa dolu")
//        }
//        else{
//            tasarim11.isVisible=true
//            tasarim22.isVisible=false
//            println("aaaaa boş")
//
//        }

        if (aygun != null) {
            if (aygun>0){

                tasarim11.isVisible=false
                tasarim22.isVisible=true

            }

            else{
                tasarim11.isVisible=true
                tasarim22.isVisible=false
            }
        }

        button=root.findViewById(R.id.btn2)
        button2=root.findViewById(R.id.btn1)

        butondelete.setOnClickListener {
            sharedPref.delete()
            tasarim11.isVisible=true
            tasarim22.isVisible=false
        }

        button.setOnClickListener {
            //gidişat
            val intent=Intent(requireContext(),MainActivity3::class.java)
            startActivity(intent)
        }

         button2.setOnClickListener {
            val intent=Intent(requireContext(),MainActivity3::class.java)
            startActivity(intent)
//            val action=HesabimFragmentDirections.actionProfilToKayitgirisFragment()
//            Navigation.findNavController(it).navigate(action)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserViewModel= ViewModelProviders.of(this).get(UserViewModel::class.java)
        val user=mUserViewModel.getuser(aygun)

        observeLiveData()
      }

    private fun observeLiveData(){
        mUserViewModel.kullanici_whit_id.observe(viewLifecycleOwner, Observer {
            if (it!=null){
                val isim=it.kullanici_adi
                text.text="Hoşgeldiniz "+isim
            }
        })
    }

 }