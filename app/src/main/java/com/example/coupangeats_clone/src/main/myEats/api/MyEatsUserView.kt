package com.example.coupangeats_clone.src.main.myEats.api

import com.example.coupangeats_clone.src.models.UserInfoResponse

interface MyEatsUserView {
    fun onGetUserSuccess(response: UserInfoResponse)

    fun onGetUserFailure(message: String)
}