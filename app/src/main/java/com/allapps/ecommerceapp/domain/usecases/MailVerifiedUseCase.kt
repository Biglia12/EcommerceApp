package com.allapps.ecommerceapp.domain.usecases

import com.allapps.ecommerceapp.data.network.FirebaseAuthRepositoryImpl

class MailVerifiedUseCase {

    private val firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl =
        FirebaseAuthRepositoryImpl()

    suspend operator fun invoke(): Boolean = firebaseAuthRepositoryImpl.checkVerifiedMail()

}

