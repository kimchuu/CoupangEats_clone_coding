package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.home.HomeFragment
import com.example.coupangeats_clone.src.models.RestaurantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetRestaurantService(val view: HomeFragment) {

    fun tryGetRestaurants(hasTag:String){
        val RetrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
            RetrofitInterface.getRestaurant("골라먹는 맛집").enqueue(object :Callback<RestaurantResponse>{
                override fun onResponse(
                    call: Call<RestaurantResponse>,
                    response: Response<RestaurantResponse>
                ) {
                    view.onGetRestaurantSuccess(response.body() as RestaurantResponse)
                }

                override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                    view.onGetRestaurantFailure(t.message ?: "통신 오류")
                }


            })
        }

    }
