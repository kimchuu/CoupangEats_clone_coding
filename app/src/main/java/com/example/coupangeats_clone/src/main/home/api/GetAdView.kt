package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.src.models.CategoryResponse
import com.example.coupangeats_clone.src.models.GetAdResponse
import com.example.coupangeats_clone.src.models.UserInfoResponse

interface GetAdView {
    fun onGetAdSuccess(response: GetAdResponse)

    fun onGetAdFailure(message: String)
}