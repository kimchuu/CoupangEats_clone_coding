package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.src.models.CategoryResponse
import com.example.coupangeats_clone.src.models.RestaurantResponse

interface GetRestaurantView {
    fun onGetRestaurantSuccess(response: RestaurantResponse)

    fun onGetRestaurantFailure(message: String)
}