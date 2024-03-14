package com.allapps.ecommerceapp.data.network

import android.content.ContentValues.TAG
import android.util.Log
import com.allapps.ecommerceapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepositoryImpl : AuthRepository {

    val firebaseAuth = FirebaseAuth.getInstance()
    val firebaseClient = FirebaseAuth.getInstance()

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
            var isSuccesful = false
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        isSuccesful = true
                    }else{
                        Log.d(TAG, "createUserWithEmail:failure", task.exception)
                    }
                }
                .await()
            isSuccesful
        } catch (e: Exception) {
            false
        }
    }
}