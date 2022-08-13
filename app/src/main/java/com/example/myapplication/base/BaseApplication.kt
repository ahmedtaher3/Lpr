package com.example.myapplication.base


import android.app.Application
import com.example.myapplication.data.shared.SharedPrefsHelper
import android.content.res.Configuration
 import com.example.myapplication.data.shared.DataManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    var dataManager: DataManager? = null


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPrefsHelper = SharedPrefsHelper(applicationContext)
        dataManager = DataManager(sharedPrefsHelper)

    }

}