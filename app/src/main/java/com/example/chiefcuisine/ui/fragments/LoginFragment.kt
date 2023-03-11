package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chiefcuisine.R
import com.example.chiefcuisine.databinding.FragmentLoginBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val loginView = inflater.inflate(R.layout.fragment_login, container, false)
        loginBinding = FragmentLoginBinding.bind(loginView)
        firebaseAuth = FirebaseAuth.getInstance()

        loginBinding.tvSignUp.setOnClickListener {
            navigateToSignUpFragment()
        }

        loginBinding.btnLogin.setOnClickListener {
            loginUser()
        }
        return loginView
    }

    private fun navigateToSignUpFragment() {
        val direction = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
        findNavController().navigate(direction)
    }

    private fun loginUser(){
        val email = loginBinding.editTextTextEmailAddress.text.toString()
        val password = loginBinding.editTextTextPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Please Enter all the Fields",
                Snackbar.LENGTH_LONG
            ).show()
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Login Success",
                    Snackbar.LENGTH_LONG
                ).show()
                activity?.let{
                    val intent = Intent (it, FoodActivity::class.java)
                    it.startActivity(intent)
                }
            }
            else{
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "Some Error Occurred!",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }


    }

}