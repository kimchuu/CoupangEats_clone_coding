package com.example.coupangeats_clone.src.main.address.api

import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.src.RetrofitInterface
import com.example.coupangeats_clone.src.main.address.SetAddressActivity
import com.example.coupangeats_clone.src.main.address.SetAdressDetailActivity
import com.example.coupangeats_clone.src.models.AddressRequest
import com.example.coupangeats_clone.src.models.AddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PatchAddressService(val view: SetAddressActivity) {

    fun tryPatchAddress(user_idx:Int, idx:Int){
        val retrofitInterface = ApplicationClass.sRetrofit.create(RetrofitInterface::class.java)
        retrofitInterface.patchRepresentAddress(user_idx,idx).enqueue(object: Callback<AddressResponse>{
            override fun onResponse(
                call: Call<AddressResponse>,
                response: Response<AddressResponse>
            ) {
                view.onPatchAddressSuccess(response.body() as AddressResponse)
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                t.message?.let { view.onPatchAddressFailure(it) }
            }

        })
    }
}