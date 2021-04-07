package com.example.coupangeats_clone.src.models

import com.example.template_practice.config.BaseResponse
import com.google.gson.annotations.SerializedName


data class AddressResponse(
        // 베이스 리스폰스를 상속 받았으므로, 아래 내용은 포함이 되었습니다.
        // @SerializedName("isSuccess") val isSuccess: Boolean,
        // @SerializedName("code") val code: Int,
        // @SerializedName("message") val message: String,
        @SerializedName("result") val result: String
) : BaseResponse()