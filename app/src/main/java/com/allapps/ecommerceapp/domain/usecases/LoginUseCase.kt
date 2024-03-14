package com.allapps.ecommerceapp.domain.usecases

import androidx.lifecycle.viewModelScope
import com.allapps.ecommerceapp.data.network.FirebaseAuthRepositoryImpl
import kotlinx.coroutines.launch

class LoginUseCase {
    private val firebaseAuthRepositoryImpl = FirebaseAuthRepositoryImpl()

    suspend fun login (email: String, pass:String){
            firebaseAuthRepositoryImpl.login(email, pass)
        }
}