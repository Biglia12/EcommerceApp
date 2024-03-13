package com.allapps.ecommerceapp.domain.repository

interface AuthRepository {

    suspend fun login(email:String, password: String)

    suspend fun signUp()

}