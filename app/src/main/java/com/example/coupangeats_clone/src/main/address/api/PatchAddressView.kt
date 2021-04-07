package com.example.coupangeats_clone.src.main.address.api

import com.example.coupangeats_clone.src.models.AddressResponse
import com.example.coupangeats_clone.src.models.SignUpResponse

interface PatchAddressView {
    fun onPatchAddressSuccess(response: AddressResponse)
    fun onPatchAddressFailure(message: String)
}