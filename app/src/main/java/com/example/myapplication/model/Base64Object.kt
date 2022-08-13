package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class Base64Object(
    @SerializedName("image")
    var image: String = ""
)
