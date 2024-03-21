package com.allapps.ecommerceapp.domain.usecases

import com.allapps.ecommerceapp.data.network.FirebaseAuthRepositoryImpl

class VerificationUseCase {

    private val firebaseAuthRepositoryImpl = FirebaseAuthRepositoryImpl()

    suspend operator fun invoke() = firebaseAuthRepositoryImpl.sendVerificationEmail()


}