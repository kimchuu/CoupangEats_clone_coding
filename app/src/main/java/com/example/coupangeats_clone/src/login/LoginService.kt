package com.example.coupangeats_clone.src.login

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.models.PostLoginRequest
import com.example.coupangeats_clone.src.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view:CoupangIdLoginActivity) {

    fun tryGetUsers(postLoginRequest: PostLoginRequest){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        loginRetrofitInterface.PostLoginUsers(postLoginRequest).enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                view.onGetUserSuccess(response.body() as UserResponse)
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                view.onGetUserFailure(t.message ?: "통신 오류")
            }

        })
    }
}