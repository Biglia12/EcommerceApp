package com.allapps.ecommerceapp.ui.views.fragments.verification

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.databinding.FragmentLoginBinding
import com.allapps.ecommerceapp.databinding.FragmentVerificationBinding
import com.allapps.ecommerceapp.utils.toast

class VerificationFragment : Fragment() {

    private val viewModel: VerificationViewModel by viewModels()
    private lateinit var binding: FragmentVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerificationBinding.inflate(inflater)


        observers()

        return binding.root

    }

    private fun observers() {

        viewModel.navToHome.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_verificationFragment_to_homeFragment)
        }

        viewModel.showError.observe(viewLifecycleOwner) {
            requireContext().toast("error en verificar email")
        }
    }
}