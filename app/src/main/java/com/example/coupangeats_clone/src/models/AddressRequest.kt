package com.example.coupangeats_clone.src.models

import android.text.Editable
import com.google.gson.annotations.SerializedName

data class AddressRequest(
    @SerializedName("address") val address: String,
    @SerializedName("detailedAddress") val detailedAddress: String,
    @SerializedName("addressType") val addressType: String

)