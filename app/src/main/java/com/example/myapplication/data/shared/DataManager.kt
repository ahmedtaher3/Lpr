package com.example.myapplication.data.shared

class DataManager(var mSharedPrefsHelper: SharedPrefsHelper) {
    fun clear() {

        mSharedPrefsHelper.clear()

    }



    ////////////////////////////////////////////////////////////////////////////////

    fun saveToken(x: String) {
        mSharedPrefsHelper.putToken(x)
    }

    val token: String get() = mSharedPrefsHelper.token!!


    ////////////////////////////////////////////////////////////////////////////////



    fun saveIsLogin(b: Boolean) {
        mSharedPrefsHelper.putIsLogin(b)
    }

    val isLogin: Boolean
        get() = mSharedPrefsHelper.isLogin


    ////////////////////////////////////////////////////////////////////////////////





}