package com.allapps.ecommerceapp.data.network

import android.util.Log
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserService {
    val db = Firebase.firestore

    suspend fun createUserTable(userSignIn: UserSignUp) = runCatching {

        val user = hashMapOf(
            "email" to userSignIn.email,
            "nickname" to userSignIn.userName,
            "realname" to userSignIn.name
        )

        db.collection("USER_COLLECTION").add(user).await()

    }.isSuccess
}