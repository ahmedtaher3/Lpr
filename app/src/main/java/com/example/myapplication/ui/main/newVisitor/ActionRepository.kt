package com.example.myapplication.ui.main.newVisitor


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.ProfileData
import com.example.myapplication.model.ScanCarData
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class ActionRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {



    suspend fun getScanCarApi(json: JsonObject): Response<BaseResponse<ScanCarData>> =
        api.scanCar(dataManager.token , json)


    suspend fun getSendActionApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<BaseResponse.Data>> =
        api.sendAction(dataManager.token , pStringMap)


}