package com.example.coupangeats_clone.src.main.home.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.home.HomeFragment
import com.example.coupangeats_clone.src.models.CategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCategoryService(val view: HomeFragment) {

    fun tryGetCategory() {
        val RetrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        RetrofitInterface.getCategory().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                view.onGetCategorySuccess(response.body() as CategoryResponse)
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                view.onGetCategoryFailure(t.message?:"통신 오류")
            }

        })
    }

}