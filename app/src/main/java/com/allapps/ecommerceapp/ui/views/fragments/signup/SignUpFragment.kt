package com.allapps.ecommerceapp.ui.views.fragments.signup

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }

        return binding.root
    }
}