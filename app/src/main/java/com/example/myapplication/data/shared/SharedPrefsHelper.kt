package com.example.myapplication.data.shared

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsHelper(context: Context?) {
    var mSharedPreferences: SharedPreferences? = null
    fun clear() {
        mSharedPreferences!!.edit().clear().apply()
    }


    fun putToken(x: String?) {
        mSharedPreferences!!.edit().putString(Token, x).apply()
    }

    val token: String?
        get() = mSharedPreferences!!.getString(Token, "")



    ////////////////////////////////////////////////////////////////////////////////
    fun putIsLogin(b: Boolean) {
        mSharedPreferences!!.edit().putBoolean(IS_LOGGED, b).apply()
    }

    val isLogin: Boolean
        get() = mSharedPreferences!!.getBoolean(IS_LOGGED, false)

    ////////////////////////////////////////////////////////////////////////////////



    companion object {

        const val MY_PREFS = "MyApplication_PREFS"
        const val Token = "Token"
        const val IS_LOGGED = "IS_LOGGED"
    }

    init {
     this.mSharedPreferences = context?.getSharedPreferences(MY_PREFS, 0);
    }

}