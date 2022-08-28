package com.example.myapplication.ui.main.fragments.setting.gates


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.GatesData
import com.example.myapplication.model.SitesData
import retrofit2.Response
import javax.inject.Inject

class ChangeGateRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {


    suspend fun getSitesApi(): Response<BaseResponse<ArrayList<SitesData>>> =
        api.getSites(dataManager.token)


    suspend fun getGatesApi(id: Int): Response<BaseResponse<ArrayList<GatesData>>> =
        api.getGates(id, dataManager.token)
}