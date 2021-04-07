package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.home.HomeFragment
import com.example.coupangeats_clone.src.models.CategoryResponse
import com.example.coupangeats_clone.src.models.GetAdResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAdService(val view: HomeFragment) {

    fun tryGetAd() {
        val RetrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        RetrofitInterface.getAd().enqueue(object : Callback<GetAdResponse> {
            override fun onResponse(call: Call<GetAdResponse>, response: Response<GetAdResponse>) {
                view.onGetAdSuccess(response.body() as GetAdResponse)
            }

            override fun onFailure(call: Call<GetAdResponse>, t: Throwable) {
                t.message?.let { view.onGetRepresentFailure(it) }
            }


        })
    }

}