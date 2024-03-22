package com.allapps.ecommerceapp.ui.views.fragments.signup

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.allapps.ecommerceapp.R
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.databinding.FragmentSignUpBinding
import com.allapps.ecommerceapp.utils.snackbar
import com.allapps.ecommerceapp.utils.toast
import com.google.android.material.textfield.TextInputLayout

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

        focusListener()
        signUp()
        initObservers()

        return binding.root
    }

    private fun focusListener() {

        binding.etEmail.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.tilEmail.helperText = validEmail()
                updateButtonState()
            }
        }

        binding.etPass.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.tilPass.helperText = validPass(binding.etPass.text.toString())
                updateButtonState()
            }
        }

        binding.etRepeatPass.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.tilRepeatPass.helperText = validPass(binding.etRepeatPass.text.toString())
                binding.tilRepeatPass.helperText = equalsPass()
                updateButtonState()
            }
        }
    }


    private fun equalsPass(): String? {
        return if (binding.etPass.text.toString() != binding.etRepeatPass.text.toString()) {
            "Las contraseñas no coinciden"
        } else null
    }

    private fun validEmail(): String? {
        return if (!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
            "Correo electrónico no válido"
        } else null
    }

    private fun validPass(pass: String): String? {
        return if (pass.length < 6) {
            "La contraseña debe contener 6 caracteres o más"
        } else null
    }


    private fun signUp() {

        val userName = binding.etNickname
        val name = binding.etName
        val email = binding.etEmail
        val pass = binding.etPass
        val passConfirmate = binding.etRepeatPass

        binding.btnRegister.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateButtonState()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No es necesario hacer nada aquí
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No es necesario hacer nada aquí
            }
        }

        userName.addTextChangedListener(textWatcher)
        name.addTextChangedListener(textWatcher)
        email.addTextChangedListener(textWatcher)
        pass.addTextChangedListener(textWatcher)
        passConfirmate.addTextChangedListener(textWatcher)

        binding.btnRegister.setOnClickListener {
            viewModel.signUp(
                UserSignUp(
                    userName.text.toString(),
                    name.text.toString(),
                    email.text.toString(),
                    pass.text.toString(),
                    passConfirmate.text.toString()
                )
            )
        }

    }

    private fun updateButtonState() {
        binding.btnRegister.isEnabled = isFormValid()
    }

    private fun isFormValid(): Boolean {
        val userName = binding.etNickname.text.toString()
        val name = binding.etName.text.toString()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPass.text.toString()
        val passConfirmate = binding.etRepeatPass.text.toString()

        return userName.isNotEmpty() &&
                name.isNotEmpty() &&
                email.isNotEmpty() &&
                pass.isNotEmpty() &&
                passConfirmate.isNotEmpty() &&
                validEmail() == null &&
                validPass(pass) == null &&
                equalsPass() == null
    }

    private fun initObservers() {
        viewModel.navToVerifyAccount.observe(viewLifecycleOwner) { accountCreated ->
            if (accountCreated) {
                findNavController().navigate(R.id.action_signUpFragment_to_verificationFragment)
            } else {
                requireContext().snackbar(binding.viewSignup, "Error al registrarse")
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.viewLoad.root.visibility = View.VISIBLE
                binding.viewSignup.isClickable = false
            } else {
                binding.viewLoad.root.visibility = View.GONE
                binding.viewSignup.isClickable = true
            }
        }
    }
}