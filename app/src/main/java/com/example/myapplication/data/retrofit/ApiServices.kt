package com.example.myapplication.data.retrofit


import com.example.myapplication.base.BaseResponse
import com.example.myapplication.model.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiServices {

    @Headers("Accept: application/json")
    @GET("api/auth/me")
    suspend fun getProfile(
        @Header("Authorization") token: String?
    ): Response<BaseResponse<UserData>>

    @Headers("Accept: application/json")
    @POST("api/auth/login")
    @FormUrlEncoded
    suspend fun login(
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<BaseResponse.Data>>

    @Headers("Accept: application/json")
    @POST("api/forgotPassword")
    @FormUrlEncoded
    suspend fun forgotPassword(
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<BaseResponse.Data>>

    @Headers("Accept: application/json")
    @POST("api/resetPassword")
    @FormUrlEncoded
    suspend fun resetPassword(
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<BaseResponse.Data>>

    @Headers("Accept: application/json")
    @GET("api/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String?
    ): Response<BaseResponse<BaseResponse.Data>>


    @Multipart
    @Headers("Accept: application/json")
    @POST("api/scanCar")
    suspend fun scanCar(
        @Header("Authorization") token: String?,
        @Part selfie: MultipartBody.Part?,
    ): Response<BaseResponse<ConfirmScanData>>


    @Headers("Accept: application/json")
    @POST("api/historyStore")
    @FormUrlEncoded
    suspend fun confirmCar(
        @Header("Authorization") token: String?,
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<ScanCarData>>


    @Headers("Accept: application/json")
    @POST("api/scanQr")
    @FormUrlEncoded
    suspend fun scanQr(
        @Header("Authorization") token: String?,
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<ScanCarData>>


    @Headers("Accept: application/json")
    @POST("api/carAction")
    @FormUrlEncoded
    suspend fun sendAction(
        @Header("Authorization") token: String?,
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<BaseResponse.Data>>

    @Headers("Accept: application/json")
    @GET("api/getHistory")
    suspend fun history(
        @Header("Authorization") token: String?,
        @Query("type") type: String,
        @Query("count") count: Int,
        @Query("gate_id") gate_id: Int,
        @Query("page") page: Int
    ): Response<BaseResponse<ArrayList<HistoryData>>>


    @Headers("Accept: application/json")
    @GET("api/sites")
    suspend fun getSites(
        @Header("Authorization") token: String?,
    ): Response<BaseResponse<ArrayList<SitesData>>>


    @Headers("Accept: application/json")
    @GET("api/gates/{id}")
    suspend fun getGates(
        @Path("id") id: Int,
        @Header("Authorization") token: String?,
    ): Response<BaseResponse<ArrayList<GatesData>>>
}