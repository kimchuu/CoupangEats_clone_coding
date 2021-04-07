package com.example.coupangeats_clone.src.register

import com.example.coupangeats_clone.src.models.SignUpResponse

interface RegisterActivityView {
    fun onPostSignUpSuccess(response: SignUpResponse)
    fun onPostSignUpFailure(message: String)
}