package com.example.coupangeats_clone.src.main.address.api

import com.example.coupangeats_clone.src.models.AddressResponse
import com.example.coupangeats_clone.src.models.GetRepresentAddressResponse
import com.example.coupangeats_clone.src.models.SignUpResponse

interface GetRepresentAddressView {
    fun onGetRepresentAddressSuccess(response: GetRepresentAddressResponse)
    fun onGetRepresentFailure(message: String)
}