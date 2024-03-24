package com.allapps.ecommerceapp.data.network

import android.util.Log
import com.allapps.ecommerceapp.EcommerceApplication
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.data.repository.UserRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class UserService {
    val db = Firebase.firestore
    var application = EcommerceApplication()
    var userRepository = UserRepository()

    suspend fun createUserTable(userSignIn: UserSignUp): Boolean {
        return try {
            val user = hashMapOf(
                "email" to userSignIn.email,
                "nickname" to userSignIn.userName,
                "realname" to userSignIn.name
            )

            db.collection("USER_COLLECTION").add(user).await()
            userRepository.setUser(userSignIn)
            true
        } catch (e: Exception) {
            Log.e("errorUserCreateTable", e.toString())
            false
        }
    }

    //    val user = hashMapOf(
    //         "email" to userSignIn.email,
    //         "nickname" to userSignIn.userName,
    ///        "realname" to userSignIn.name
    // )

    //db.collection("USER_COLLECTION").add(user).await()

    //Log.d("UserService", this@UserService.toString())

    //application.saveUser(userSignIn)

    //}.isSuccess
}