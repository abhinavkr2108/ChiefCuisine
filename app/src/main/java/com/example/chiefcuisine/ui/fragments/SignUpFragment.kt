package com.example.chiefcuisine.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chiefcuisine.R
import com.example.chiefcuisine.databinding.FragmentSignUpBinding
import com.example.chiefcuisine.ui.activities.FoodActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sign

class SignUpFragment : Fragment() {
    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val signupView = inflater.inflate(R.layout.fragment_sign_up, container, false)
        signUpBinding = FragmentSignUpBinding.bind(signupView)
        firebaseAuth = FirebaseAuth.getInstance()

        signUpBinding.tvLogin.setOnClickListener {
            navigateToLoginFragment()
        }

        signUpBinding.btnSignUp.setOnClickListener {
            uiScope.launch(Dispatchers.IO) {
                signUpUser()
            }

        }

        return signupView
    }

    private fun navigateToLoginFragment() {
        val direction = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
        findNavController().navigate(direction)
    }

    private fun signUpUser(){
        val name = signUpBinding.etName.text.toString()
        val email = signUpBinding.editTextTextEmailAddress.text.toString()
        val password = signUpBinding.editTextTextPassword.text.toString()
        val confirmPassword = signUpBinding.editTextConfirmPassword.text.toString()


        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Please Enter all the Fields",
                Snackbar.LENGTH_LONG
            ).show()
        }

       /* if (password.length < 7){
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Password Should Have More Than 7 Characters",
                Snackbar.LENGTH_LONG
            ).show()
        }*/

        if (password!=confirmPassword){
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "Password and Confirm Password don't match",
                Snackbar.LENGTH_LONG
            ).show()
        }



        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    "SignUp Success",
                    Snackbar.LENGTH_LONG
                ).show()

                // Navigate to Food Activity
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