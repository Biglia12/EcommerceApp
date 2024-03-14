package com.allapps.ecommerceapp.ui.views.fragments.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allapps.ecommerceapp.data.network.FirebaseAuthRepositoryImpl
import com.allapps.ecommerceapp.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
   // private val firebaseAuthRepositoryImpl = FirebaseAuthRepositoryImpl()
    private val loginUseCase = LoginUseCase()

    suspend fun login (email: String, pass:String){
        viewModelScope.launch {
            loginUseCase.login(email, pass)
        }
    }
}