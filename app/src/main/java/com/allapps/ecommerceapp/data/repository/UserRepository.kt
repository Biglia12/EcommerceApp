package com.allapps.ecommerceapp.data.repository

import com.allapps.ecommerceapp.EcommerceApplication
import com.allapps.ecommerceapp.data.model.User
import com.allapps.ecommerceapp.data.model.UserSignUp

class UserRepository {

    fun setUser(user: UserSignUp) {
        val pref = EcommerceApplication.prefs.setUser(user)
    }

    fun getUser(): User? {
        val userPref = EcommerceApplication.prefs.getUser()
        if (userPref != null && !userPref.email.isNullOrEmpty() && !userPref.name.isNullOrEmpty()) {
            return userPref
        }
        return null

    }
}