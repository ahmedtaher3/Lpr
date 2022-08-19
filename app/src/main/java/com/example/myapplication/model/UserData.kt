package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

data class UserData(

	@field:SerializedName("roles")
	val roles: List<String?>? = null,

	@field:SerializedName("user")
	val user: User? = null
)

data class Pivot(

	@field:SerializedName("role_id")
	val roleId: Int? = null,

	@field:SerializedName("model_type")
	val modelType: String? = null,

	@field:SerializedName("model_id")
	val modelId: Int? = null
)

data class User(

	@field:SerializedName("code")
	val code: Any? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("company_id")
	val companyId: Any? = null,

	@field:SerializedName("department_id")
	val departmentId: Int? = null,

	@field:SerializedName("roles")
	val roles: List<RolesItem?>? = null,

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

data class RolesItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("pivot")
	val pivot: Pivot? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("guard_name")
	val guardName: String? = null
)
