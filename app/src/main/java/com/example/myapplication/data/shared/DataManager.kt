package com.example.myapplication.data.shared

import com.example.myapplication.model.GatesData
import com.example.myapplication.model.SitesData
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


    fun saveSite(b: String) {
        mSharedPrefsHelper.putSite(b)
    }

    val site: SitesData?
        get() = mSharedPrefsHelper.site.toObjectFromJson<SitesData>(SitesData::class.java)


    ////////////////////////////////////////////////////////////////////////////////


    fun saveGate(b: String) {
        mSharedPrefsHelper.putGate(b)
    }

    val gate: GatesData
        get() = mSharedPrefsHelper.gate.toObjectFromJson<GatesData>(GatesData::class.java)


    ////////////////////////////////////////////////////////////////////////////////



}