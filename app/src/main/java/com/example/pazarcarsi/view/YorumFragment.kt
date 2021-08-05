package com.example.pazarcarsi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import com.ebanx.swipebtn.SwipeButton
import com.example.pazarcarsi.R
import kotlinx.android.synthetic.main.fragment_yorum.*

class YorumFragment : Fragment() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_yorum, container, false)

        val swipe=root.findViewById<SwipeButton>(R.id.swipe_button)

        swipe.setOnStateChangeListener {

            if (swipe.isActive){
                 println("deneme1")
                val action=YorumFragmentDirections.actionYorumFragmentToProfil()
                view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
             }

         }

        return root
    }

 }