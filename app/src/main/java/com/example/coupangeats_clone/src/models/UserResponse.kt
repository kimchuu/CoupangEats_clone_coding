package com.example.coupangeats_clone.src.models

import com.example.template_practice.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserResponse(
//        @SerializedName("isSuccess") val isSuccess: Boolean,
//        @SerializedName("code") val code: Int,
//        @SerializedName("message") val message: String,
        //@SerializedName("result") val result: ArrayList<ResultUser>
        @SerializedName("result") val result: ResultJwt
):BaseResponse()
