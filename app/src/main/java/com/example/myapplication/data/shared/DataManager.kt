package com.example.myapplication.data.shared

import com.example.myapplication.model.UserData
import com.example.myapplication.util.extensions.toObjectFromJson

class DataManager(var mSharedPrefsHelper: SharedPrefsHelper) {
    fun clear() {

        mSharedPrefsHelper.clear()

    }



    ////////////////////////////////////////////////////////////////////////////////

    fun saveToken(x: String) {
        mSharedPrefsHelper.putToken("bearer $x")
    }

    val token: String get() = mSharedPrefsHelper.token!!


    ////////////////////////////////////////////////////////////////////////////////



    fun saveIsLogin(b: Boolean) {
        mSharedPrefsHelper.putIsLogin(b)
    }

    val isLogin: Boolean
        get() = mSharedPrefsHelper.isLogin


    ////////////////////////////////////////////////////////////////////////////////


    fun saveUser(b: String) {
        mSharedPrefsHelper.putUser(b)
    }

    val user: UserData?
        get() = mSharedPrefsHelper.user.toObjectFromJson<UserData>(UserData::class.java)


    ////////////////////////////////////////////////////////////////////////////////



}