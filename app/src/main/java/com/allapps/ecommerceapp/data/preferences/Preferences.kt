package com.allapps.ecommerceapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.allapps.ecommerceapp.data.model.User
import com.allapps.ecommerceapp.data.model.UserSignUp

class Preferences(val context: Context) {

    fun setUser(userData: UserSignUp) {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.putString("email", userData.email)
        editor.putString("nickname", userData.userName)
        editor.putString("realname", userData.name)
        editor.apply()
    }

    fun getUser(): User? {
        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val email = sharedPref.getString("email", null)
        val nickName = sharedPref.getString("nickname", null)
        val realName = sharedPref.getString("realname", null)

        return if (email != null && nickName != null && realName != null) {
            User(realName, email, nickName)
        } else {
            null
        }
    }
}