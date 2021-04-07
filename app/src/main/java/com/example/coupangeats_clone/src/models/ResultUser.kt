package com.example.coupangeats_clone.src.models

import com.google.gson.annotations.SerializedName

data class ResultUser(
        @SerializedName("userIdx") val userIdx: Int,
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String
)
