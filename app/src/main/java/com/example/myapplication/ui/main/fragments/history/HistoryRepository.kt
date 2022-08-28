package com.example.myapplication.ui.main.fragments.history


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.HistoryData
import com.example.myapplication.util.extensions.ifNull
import com.example.myapplication.util.extensions.ifNullOrZero
import retrofit2.Response
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {


    suspend fun getHistoryApi(type: String, page: Int): Response<BaseResponse<ArrayList<HistoryData>>> =
        api.history(dataManager.token, type, 10, dataManager.gate?.id.ifNull(), page)


}