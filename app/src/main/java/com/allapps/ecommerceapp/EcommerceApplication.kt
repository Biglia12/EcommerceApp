package com.allapps.ecommerceapp

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences
import com.allapps.ecommerceapp.data.model.User
import com.allapps.ecommerceapp.data.model.UserSignUp
import com.allapps.ecommerceapp.data.preferences.Preferences


class EcommerceApplication : Application() {

    companion object {
        lateinit var prefs: Preferences
    }
    override fun onCreate() {
        super.onCreate()

        prefs = Preferences(applicationContext)

    }

    fun saveUser(userData: UserSignUp) {
        val sharedPref: SharedPreferences = getDefaultSharedPreferences(this)
        val editor = sharedPref.edit()
        editor.putString("email", userData.email)
        editor.putString("nickname", userData.userName)
        editor.putString("realname", userData.name)
        editor.apply()
    }

    fun getUser(): User? {
        val sharedPref: SharedPreferences = getDefaultSharedPreferences(this)
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