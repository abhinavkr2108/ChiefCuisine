package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.chiefcuisine.R
import com.example.chiefcuisine.adapters.OnBoardingScreenAdapter
import com.example.chiefcuisine.databinding.FragmentOnBoardingBinding
import com.example.chiefcuisine.onBoardingScreens.FirstScreen
import com.example.chiefcuisine.onBoardingScreens.SecondScreen
import com.example.chiefcuisine.onBoardingScreens.ThirdScreen
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.google.firebase.auth.FirebaseAuth
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardingFragment : Fragment() {
    lateinit var userAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userAuth = FirebaseAuth.getInstance()

        if (userAuth.currentUser!= null){
            redirectUser("MAIN")
        }
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        val boardBinding = FragmentOnBoardingBinding.bind(view)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = OnBoardingScreenAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        boardBinding.viewPagerScreen.adapter = adapter

        boardBinding.dotsIndicator.attachTo(boardBinding.viewPagerScreen)

        boardBinding.btnGetStarted.setOnClickListener {
            redirectUser("LOGIN")
        }

        return view
    }

    private fun redirectUser(status: String){
        if (status == "LOGIN"){
            val direction = OnBoardingFragmentDirections.actionOnBoardingFragmentToWelcomeFragment()
            findNavController().navigate(direction)
        }

        if (status == "MAIN"){
            val intent = Intent(activity, FoodActivity::class.java)
            activity?.startActivity(intent)
        }

//        else{
//            throw Exception("no path exists")
//        }

    }

}