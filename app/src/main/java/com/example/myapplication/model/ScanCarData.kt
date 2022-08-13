package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScanCarData(

	@field:SerializedName("driver_name")
	val driverName: String? = null,

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("driver_type")
	val driverType: String? = null,

	@field:SerializedName("car_plate_ar")
	val carPlateAr: String? = null,

	@field:SerializedName("car_brand")
	val carBrand: String? = null,

	@field:SerializedName("car_request_id")
	val carRequestId: Int? = null,

	@field:SerializedName("car_id")
	val carId: Int? = null,

	@field:SerializedName("car_plate_en")
	val carPlateEn: String? = null
) : Parcelable
