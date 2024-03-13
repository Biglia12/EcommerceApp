package com.allapps.ecommerceapp.ui.views.fragments.welcome

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {


    private val viewModel: WelcomeViewModel by viewModels()
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater)


        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signUpFragment)
        }


        return binding.root

    }
}