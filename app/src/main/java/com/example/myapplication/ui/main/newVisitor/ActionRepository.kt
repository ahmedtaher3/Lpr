package com.example.myapplication.ui.main.newVisitor


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.ConfirmScanData
import com.example.myapplication.model.ScanCarData
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

class ActionRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {


    suspend fun getScanCarApi(image: MultipartBody.Part?): Response<BaseResponse<ConfirmScanData>> =
        api.scanCar(dataManager.token, image)

    suspend fun getConfirmCarApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<ScanCarData>> {

        pStringMap["gate_id"] = dataManager.gate?.id.toString()
        return api.confirmCar(dataManager.token, pStringMap)
    }


    suspend fun getScanQrApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<ScanCarData>> {

        pStringMap["gate_id"] = dataManager.gate?.id.toString()
        return api.scanQr(dataManager.token, pStringMap)
    }


    suspend fun getSendActionApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<BaseResponse.Data>> =
        api.sendAction(dataManager.token, pStringMap)


}