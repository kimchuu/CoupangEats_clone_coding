package com.example.coupangeats_clone.src.main.myEats.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.myEats.MyEatsFragment
import com.example.coupangeats_clone.src.models.UserInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetUserService(val view:MyEatsFragment) {

    fun tryGetUsers(){
        val RetrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        ApplicationClass.sSharedPreferences.getString(X_ACCESS_TOKEN,"")?.let {
            RetrofitInterface.getUserEats(it).enqueue(object :Callback<UserInfoResponse>{
                override fun onResponse(
                    call: Call<UserInfoResponse>,
                    response: Response<UserInfoResponse>
                ) {
                    view.onGetUserSuccess(response.body() as UserInfoResponse)
                }

                override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                    view.onGetUserFailure(t.message ?: "통신 오류")
                }

            })
        }

    }
}