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


    fun putUser(b: String) {
        mSharedPreferences!!.edit().putString(USER  , b).apply()
    }

    val user: String?
        get() =  mSharedPreferences!!.getString(USER, "")

    ////////////////////////////////////////////////////////////////////////////////

    fun putSite(b: String) {
        mSharedPreferences!!.edit().putString(SITE  , b).apply()
    }

    val site: String?
        get() =  mSharedPreferences!!.getString(SITE, "")

    ////////////////////////////////////////////////////////////////////////////////


    fun putGate(b: String) {
        mSharedPreferences!!.edit().putString(GATE  , b).apply()
    }

    val gate: String?
        get() =  mSharedPreferences!!.getString(GATE, "")

    ////////////////////////////////////////////////////////////////////////////////


    companion object {

        const val MY_PREFS = "MyApplication_PREFS"
        const val SITE = "SITE"
        const val Token = "Token"
        const val GATE = "GATE"
        const val IS_LOGGED = "IS_LOGGED"
        const val USER = "USER ="
    }

    init {
     this.mSharedPreferences = context?.getSharedPreferences(MY_PREFS, 0);
    }

}