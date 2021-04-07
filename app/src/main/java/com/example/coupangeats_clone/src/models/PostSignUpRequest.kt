package com.example.coupangeats_clone.src.models

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("name") val name:String
)
