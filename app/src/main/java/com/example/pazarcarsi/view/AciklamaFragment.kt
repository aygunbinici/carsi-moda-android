package com.example.pazarcarsi.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
 import com.example.pazarcarsi.R
import kotlinx.android.synthetic.main.fragment_aciklama.*


class AciklamaFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_aciklama, container, false)


        val ok=root.findViewById<TextView>(R.id.geriback)

        ok.setOnClickListener() {
            activity?.onBackPressed()
        }


         return root
    }

}



