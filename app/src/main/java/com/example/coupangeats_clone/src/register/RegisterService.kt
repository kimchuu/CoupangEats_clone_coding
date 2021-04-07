package com.example.coupangeats_clone.src.register

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.models.PostSignUpRequest
import com.example.coupangeats_clone.src.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 회원가입 서버로부터 받아오기
class RegisterService(val view:RegisterActivity) {

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        retrofitInterface.postSignUp(postSignUpRequest).enqueue(object: Callback<SignUpResponse>{
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }

        })
    }
}