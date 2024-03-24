package com.allapps.ecommerceapp.data.network

import android.content.ContentValues.TAG
import android.util.Log
import com.allapps.ecommerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import kotlin.concurrent.timerTask
import kotlin.math.log

class FirebaseAuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    //val firebaseStore = FirebaseFirestore.getInstance()

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

    override suspend fun sendVerificationEmail(): Boolean = runCatching {
        firebaseAuth.currentUser?.sendEmailVerification()?.await() ?: false
    }.fold(
        onSuccess = {
            Log.d("sendEmailVerification", "verification succesful")
            true
        },
        onFailure = {
            Log.d("sendEmailVerification", "error: $it")
            false
        }
    )

    override suspend fun verifiedMail(): Boolean {
        firebaseAuth.currentUser?.reload()?.await()
        return firebaseAuth.currentUser?.isEmailVerified ?: false
    }

    suspend fun checkVerifiedMail(): Boolean {
        while (true) {
            if (verifiedMail()) {
                // El correo electrónico ha sido verificado
                Log.d("verifiedMail", "¡El correo electrónico ha sido verificado!")
                return true // Devuelve true
            } else {
                // El correo electrónico no ha sido verificado
                Log.d(
                    "verifiedMail",
                    "El correo electrónico aún no ha sido verificado. Consultando de nuevo en 5 segundos..."
                )
                delay(1000) // Espera de 5 segundos antes de consultar nuevamente
            }
        }

    }


}

