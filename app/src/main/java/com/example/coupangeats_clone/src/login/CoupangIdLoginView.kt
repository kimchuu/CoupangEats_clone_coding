package com.example.coupangeats_clone.src.login

import com.example.coupangeats_clone.src.models.UserResponse

interface CoupangIdLoginView {
    fun onGetUserSuccess(response: UserResponse)

    fun onGetUserFailure(message: String)
}