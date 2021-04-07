package com.example.coupangeats_clone.src

import com.example.coupangeats_clone.src.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @POST("/app/users/login")
    fun PostLoginUsers(@Body params:PostLoginRequest):Call<UserResponse>

    @POST("/app/users/signin")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

    @GET("/app/users")
    fun getUserEats(@Header("X-ACCESS-TOKEN") jwt:String):Call<UserInfoResponse>

    @GET("/app/restaurants/home")
    fun getRestaurant(@Query("hashTag") hashTag:String):Call<RestaurantResponse>

    @GET("/app/restaurants/search-main")
    fun getCategory():Call<CategoryResponse>

    @POST("/app/users/address")
    fun postAddress(
        @Header("X-ACCESS-TOKEN") jwt:String,
        @Body params: AddressRequest
    ) : Call<AddressResponse>

    @GET("/app/users/address")
    fun getRepresentAddress(@Header("X-ACCESS-TOKEN") jwt:String):Call<GetRepresentAddressResponse>

    @PATCH("/app/users/address")
    fun patchRepresentAddress(
        @Header("user-idx") user_idx:Int,
        @Body idx:Int
    ):Call<AddressResponse>

    @GET("/app/restaurants/home/ad")
    fun getAd():Call<GetAdResponse>

}