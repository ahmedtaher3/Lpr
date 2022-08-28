package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class SitesData(

	@field:SerializedName("location_name")
	val locationName: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("pivot")
	val pivot: SitesPivot? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class SitesPivot(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("site_id")
	val siteId: Int? = null
)
