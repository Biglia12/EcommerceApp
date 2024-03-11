package com.allapps.ecommerceapp.ui.views.fragments.splash

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {


    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding:FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater)

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        },2000)

        return binding.root
       // return inflater.inflate(R.layout.fragment_splash, container, false)


    }
}