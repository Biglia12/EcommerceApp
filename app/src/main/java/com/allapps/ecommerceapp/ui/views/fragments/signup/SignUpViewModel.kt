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

    private val _navToVerifyAccount = MutableLiveData<Boolean>()
    val navToVerifyAccount : LiveData<Boolean> get() = _navToVerifyAccount

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun signUp(user: UserSignUp) {
        viewModelScope.launch {
            _loading.value = true
            val succesfullAcount = signUpUseCase.signUp(user)
            if (succesfullAcount) {
                _navToVerifyAccount .value = true
                //_accountCreated.value = true
            } else {
                _navToVerifyAccount .value = false
                //_accountCreated.value = false
            }
            _loading.value = false
        }

    }
}