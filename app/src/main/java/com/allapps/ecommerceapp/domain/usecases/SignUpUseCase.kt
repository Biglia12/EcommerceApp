package com.allapps.ecommerceapp.domain.usecases

import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.data.network.FirebaseAuthRepositoryImpl
import com.allapps.ecommerceapp.data.network.UserService

class SignUpUseCase {
    private val firebaseAuthRepositoryImpl = FirebaseAuthRepositoryImpl()
    private val userService = UserService()

    suspend fun signUp(user: UserSignUp): Boolean {
        val accountCreated = firebaseAuthRepositoryImpl.signUp(user.email, user.pass)
        return if (accountCreated) {
            userService.createUserTable(user)
        } else {
            false
        }
    }

}