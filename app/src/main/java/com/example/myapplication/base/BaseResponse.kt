package com.example.myapplication.base

import com.example.myapplication.model.MetaModel
import com.example.myapplication.util.extensions.ifNullOrZero
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(

    @field:SerializedName("status")
    var status: Int? = null,

    @SerializedName("message")
    @Expose
    val message: String = "",

    @SerializedName("data")
    @Expose
    var data: T? = null,

    @SerializedName("meta")
    @Expose
    var meta: MetaModel? = null,

    @SerializedName("access_token")
    @Expose
    val accessToken: String = "",

    @SerializedName("token_type")
    @Expose
    val tokenType: String = "",

    @SerializedName("expires_in")
    @Expose
    val expiresIn: Long = 0,


    ) {
    val isSuccessResponse: Boolean
        get() {
            return status.ifNullOrZero() in 200..300
        }

val ifUnAuth : Boolean get() {
        return status == 403
    }

    val messageResponse: String
        get() {
            return message.ifEmpty {
                "Error"
            }
        }

    data class Data(val text: String = "")


}

