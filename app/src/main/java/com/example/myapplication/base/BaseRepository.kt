package com.example.myapplication.base

import com.example.myapplication.data.shared.DataManager

import android.content.Context
import com.example.myapplication.util.ConnectivityUtils


abstract class BaseRepository(private val dataManager: DataManager, private var context: Context) {


    private val connectivityUtils: ConnectivityUtils = ConnectivityUtils(context)

    fun isNetworkConnected(): Boolean {
        return connectivityUtils.isConnected()
    }

    fun getString(i: Int): String {
        return context.getString(i)
    }

}