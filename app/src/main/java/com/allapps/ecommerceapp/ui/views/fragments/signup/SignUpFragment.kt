package com.allapps.ecommerceapp.ui.views.fragments.signup

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.databinding.FragmentSignUpBinding
import com.allapps.ecommerceapp.utils.snackbar
import com.allapps.ecommerceapp.utils.toast

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

        signUp()
        initObservers()

        return binding.root
    }

    private fun signUp() {
        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
                UserSignUp(
                    userName = binding.etNickname.text.toString(),
                    name = binding.etName.text.toString(),
                    email = binding.etEmail.text.toString(),
                    pass = binding.etPass.text.toString(),
                    passConfirmate = binding.etRepeatPass.text.toString()
                )
            )
        }

    }

    private fun initObservers() {
        viewModel.navToVerifyAccount.observe(viewLifecycleOwner) { accountCreated ->
            if (accountCreated){
                findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
            }else{
                requireContext().snackbar(binding.viewSignup, "Error al registrarse")
            }
        }

       /* viewModel.accountCreated.observe(viewLifecycleOwner) { accountCreated ->
            if (accountCreated) {
                requireContext().snackbar(binding.viewSignup, "Usuario Registrado")
            } else {
                requireContext().snackbar(binding.viewSignup, "Error al registrarse")
            }

        }*/

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.viewLoad.root.visibility = View.VISIBLE
                binding.viewSignup.isClickable = false
            }else{
                binding.viewLoad.root.visibility = View.GONE
                binding.viewSignup.isClickable = true
            }
        }
    }
}