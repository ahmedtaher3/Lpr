package com.example.myapplication.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ScanCarData(

	@field:SerializedName("driver_id")
	val driverId: Int? = null,

	@field:SerializedName("driver_type")
	val driverType: String? = null,

	@field:SerializedName("checkout_note")
	val checkoutNote: String? = null,

	@field:SerializedName("car_request_id")
	val carRequestId: Int? = null,

	@field:SerializedName("history_id")
	val historyId: Int? = null,

	@field:SerializedName("car_plate_en")
	val carPlateEn: String? = null,

	@field:SerializedName("checkout_date")
	val checkoutDate: String? = null,

	@field:SerializedName("refuse_note")
	val refuseNote: String? = null,

	@field:SerializedName("car_description")
	val carDescription: String? = null,

	@field:SerializedName("driver_name")
	val driverName: String? = null,

	@field:SerializedName("status_action")
	val statusAction: String? = null,

	@field:SerializedName("car_plate_ar")
	val carPlateAr: String? = null,

	@field:SerializedName("checkin_date")
	val checkinDate: String? = null,

	@field:SerializedName("car_brand")
	val carBrand: String? = null,

	@field:SerializedName("car_id")
	val carId: Int? = null,

	@field:SerializedName("history_status")
	val historyStatus: String? = null,

	@field:SerializedName("driver_photo")
	val driverPhoto: String? = null,

	@field:SerializedName("checkin_note")
	val checkinNote: String? = null
) : Parcelable
