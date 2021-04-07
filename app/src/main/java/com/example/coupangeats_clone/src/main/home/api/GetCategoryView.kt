package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.src.models.CategoryResponse
import com.example.coupangeats_clone.src.models.UserInfoResponse

interface GetCategoryView {
    fun onGetCategorySuccess(response: CategoryResponse)

    fun onGetCategoryFailure(message: String)
}