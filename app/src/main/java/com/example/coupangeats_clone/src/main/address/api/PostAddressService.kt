package com.example.coupangeats_clone.src.main.address.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.address.SetAdressDetailActivity
import com.example.coupangeats_clone.src.models.AddressRequest
import com.example.coupangeats_clone.src.models.AddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 회원가입 서버로부터 받아오기
class PostAddressService(val view: SetAdressDetailActivity) {

    fun tryPostAddress(jwt:String, postAddressRequest: AddressRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        retrofitInterface.postAddress(jwt,postAddressRequest).enqueue(object: Callback<AddressResponse>{
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {
                view.onPostAddressSuccess(response.body() as AddressResponse)
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                t.message?.let { view.onPostAddressFailure(it) }
            }

        })
    }
}