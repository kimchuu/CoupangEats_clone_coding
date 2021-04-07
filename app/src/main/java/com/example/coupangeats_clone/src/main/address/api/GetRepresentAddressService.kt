package com.example.coupangeats_clone.src.main.address.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.address.SetAdressDetailActivity
import com.example.coupangeats_clone.src.main.home.HomeFragment
import com.example.coupangeats_clone.src.models.AddressRequest
import com.example.coupangeats_clone.src.models.AddressResponse
import com.example.coupangeats_clone.src.models.GetRepresentAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// 회원가입 서버로부터 받아오기
class GetRepresentAddressService(val view: HomeFragment) {

    fun tryGetRepresentAddress(jwt:String){
        val retrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        retrofitInterface.getRepresentAddress(jwt).enqueue(object: Callback<GetRepresentAddressResponse>{
            override fun onResponse(
                call: Call<GetRepresentAddressResponse>,
                response: Response<GetRepresentAddressResponse>
            ) {
                view.onGetRepresentAddressSuccess(response.body() as GetRepresentAddressResponse)
            }

            override fun onFailure(call: Call<GetRepresentAddressResponse>, t: Throwable) {
                t.message?.let { view.onGetRepresentFailure(it) }
            }


        })
    }
}