package com.allapps.ecommerceapp.ui.views.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.domain.usecases.SignUpUseCase
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val signUpUseCase = SignUpUseCase()

    private val _accountCreated = MutableLiveData<Boolean>()
    val accountCreated: LiveData<Boolean> get() = _accountCreated

    fun signUp(user: UserSignUp) {
        viewModelScope.launch {
            val succesfullAcount = signUpUseCase.signUp(user)
            if (succesfullAcount){
                _accountCreated.value = true
            }else{
                _accountCreated.value = false
            }
        }

    }
}