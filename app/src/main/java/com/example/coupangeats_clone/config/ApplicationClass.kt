package com.example.coupangeats_clone.config

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.example.coupangeats_clone.util.MyContext
import com.example.template_practice.config.XAccessTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// 앱 실행될 때 1번만 실행
class ApplicationClass:Application() {

    val API_URL = "https://nick-space.site"

    // 전역변수
    companion object{
        lateinit var sSharedPreferences: SharedPreferences

        // JWT token header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        // Retrofit 인스턴스
        lateinit var sRetrofit: Retrofit

        val TAG: String = "로그"
    }

    // 앱이 처음 생성되는 순간 , sp 만들고 retrofit instance 생성
    override fun onCreate() {
        super.onCreate()

        sSharedPreferences = applicationContext.getSharedPreferences("COUPANG_EATS",
            0)
        // 레트로핏 인스턴스 생성
        initRetrofitInstance()

        MyContext.setContext(this)
    }

    // 레트로핏 인스턴스 생성, 설정값 지정
    // 연결 타임아웃 5초, HttpLogginInterceptor를 붙여 어떤 요청이 나가고 들어오는지 보여줌
    fun initRetrofitInstance(){
        Log.d(TAG,"ApplicationClass - initRetrofitInstance() called")
        val client:OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
        // 로그 캣에 okhttp.OkHttpClient 검색하면 http 통신 내용 보여주게
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // jwt 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, interceptor , gson 넣어주고 빌드
        // sRetrofit 변수로 http 요청 서버로 보내면 됨
        sRetrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}