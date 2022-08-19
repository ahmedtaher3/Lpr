package com.example.myapplication.ui.main.fragments.setting


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.HistoryData
import com.example.myapplication.model.ProfileData
import com.example.myapplication.model.ScanCarData
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class SettingRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {



    suspend fun getLogoutApi(): Response<BaseResponse<BaseResponse.Data>> =
        api.logout(dataManager.token)



}