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

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navController.navigateUp()
        }

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
}