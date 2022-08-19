package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfirmScanData(

	@field:SerializedName("plate_ar")
	val plateAr: String? = null,

	@field:SerializedName("plate_en")
	val plateEn: String? = null
) : Parcelable
