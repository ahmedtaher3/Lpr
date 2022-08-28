package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class GatesData(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: Any? = null,

	@field:SerializedName("pivot")
	val pivot: GatesPivot? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class GatesPivot(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("gate_id")
	val gateId: Int? = null
)
