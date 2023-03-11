package com.example.chiefcuisine.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chiefcuisine.R
import com.example.chiefcuisine.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val welcomeView = inflater.inflate(R.layout.fragment_welcome, container, false)
        val welcomeBinding = FragmentWelcomeBinding.bind(welcomeView)

        welcomeBinding.btnLoginAccount.setOnClickListener {
            navigateToLoginFragment()
        }

        welcomeBinding.btnSignUpAccount.setOnClickListener {
            navigateToSignUpFragment()
        }

        return welcomeView
    }

    private fun navigateToLoginFragment() {
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        findNavController().navigate(direction)
    }

    private fun navigateToSignUpFragment(){
        val direction = WelcomeFragmentDirections.actionWelcomeFragmentToSignUpFragment()
        findNavController().navigate(direction)
    }

}