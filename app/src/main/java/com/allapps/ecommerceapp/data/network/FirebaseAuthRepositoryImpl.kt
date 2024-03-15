package com.allapps.ecommerceapp.data.network

import android.content.ContentValues.TAG
import android.util.Log
import com.allapps.ecommerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import kotlin.concurrent.timerTask

class FirebaseAuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()

    override suspend fun login(email: String, password: String): Boolean {
        try {
            var isSuccesful = false
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { isSuccesful = true }
                .addOnFailureListener { isSuccesful = false }
                .await()
            return isSuccesful
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.d(TAG, "createUserWithEmailAndPassword:failure", e)
            false
        }
    }
}