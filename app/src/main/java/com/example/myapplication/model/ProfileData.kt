package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class ProfileData(

	@field:SerializedName("code")
	val code: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("company_id")
	val companyId: Any? = null,

	@field:SerializedName("department_id")
	val departmentId: Int? = null,

	@field:SerializedName("last_name")
	val lastName: Any? = null,

	@field:SerializedName("photo")
	val photo: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("full_name")
	val fullName: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
