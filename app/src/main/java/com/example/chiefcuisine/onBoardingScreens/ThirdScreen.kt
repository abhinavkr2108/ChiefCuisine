package com.example.chiefcuisine.onBoardingScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.chiefcuisine.R
import com.example.chiefcuisine.ui.fragments.OnBoardingFragmentDirections
import kotlinx.android.synthetic.main.fragment_third_screen.*


class ThirdScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_third_screen, container, false)




    }

}