package com.example.myapplication.data.retrofit


import com.example.myapplication.base.BaseResponse
import com.example.myapplication.model.ProfileData
import com.example.myapplication.model.ScanCarData
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.*


interface ApiServices {

    @Headers("Accept: application/json")
    @POST("api/auth/me")
    suspend fun getProfile(
        @Header("Authorization") token: String?
    ): Response<BaseResponse<ProfileData>>

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
    @POST("api/auth/logout")
    suspend fun logout(
        @Header("Authorization") token: String?
    ): Response<BaseResponse<BaseResponse.Data>>


    @Headers("Accept: application/json")
    @POST("api/scanCar")
    suspend fun scanCar(
        @Header("Authorization") token: String?,
        @Body json: JsonObject
    ): Response<BaseResponse<ScanCarData>>


    @Headers("Accept: application/json")
    @POST("api/carAction")
    @FormUrlEncoded
    suspend fun sendAction(
        @Header("Authorization") token: String?,
        @FieldMap pStringMap: MutableMap<String, String>
    ): Response<BaseResponse<BaseResponse.Data>>
}