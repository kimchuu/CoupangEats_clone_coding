package com.example.coupangeats_clone.src.models

import com.google.gson.annotations.SerializedName

data class ResultJwt(
        @SerializedName("userIdx") val userIdx: Int,
        @SerializedName("jwt") val jwt: String
)