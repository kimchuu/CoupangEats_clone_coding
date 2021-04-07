package com.example.template_practice.config

import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class XAccessTokenInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder:Request.Builder = chain.request().newBuilder()
        val jwtToken:String? = sSharedPreferences.getString(X_ACCESS_TOKEN,null)

        if (jwtToken !=null){
            builder.addHeader("X-ACCESS_TOKEN",jwtToken)
        }
        return chain.proceed(builder.build())
    }
}