package com.allapps.ecommerceapp.ui.views.fragments.verification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allapps.ecommerceapp.domain.usecases.MailVerifiedUseCase
import com.allapps.ecommerceapp.domain.usecases.VerificationUseCase
import kotlinx.coroutines.launch

class VerificationViewModel : ViewModel() {

    private val useCaseVerification = VerificationUseCase()
    private val mailVerifiedUseCase = MailVerifiedUseCase()

    private val _navToHome = MutableLiveData<Boolean>()
    val navToHome: LiveData<Boolean> get() = _navToHome

    private val _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> get() = _showError

    init {
        viewModelScope.launch { useCaseVerification() }
        viewModelScope.launch {
            if (mailVerifiedUseCase()) {
                _navToHome.value = true

            } else {
                _showError.value = true
            }

        }

    }

}