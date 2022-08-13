package com.example.myapplication.ui.auth


import android.content.Context
import com.example.myapplication.base.BaseRepository
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.retrofit.ApiServices
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.model.ProfileData
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val context: Context,
    private val dataManager: DataManager,
    private val api: ApiServices
) : BaseRepository(dataManager, context) {



    suspend fun getLoginApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<BaseResponse.Data>> =
        api.login(pStringMap)

    suspend fun getProfileApi(): Response<BaseResponse<ProfileData>> =
        api.getProfile(dataManager.token)


    suspend fun getForgetPasswordApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<BaseResponse.Data>> =
        api.forgotPassword(pStringMap)

    suspend fun getResetPasswordApi(pStringMap: MutableMap<String, String>): Response<BaseResponse<BaseResponse.Data>> =
        api.resetPassword(pStringMap)

    suspend fun getLogoutApi(): Response<BaseResponse<BaseResponse.Data>> =
        api.logout(dataManager.token)
}