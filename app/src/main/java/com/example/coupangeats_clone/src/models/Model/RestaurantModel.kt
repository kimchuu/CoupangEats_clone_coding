package com.example.coupangeats_clone.src.models.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class RestaurantModel(var image:String, var image_2:String?=null, var image_3:String?=null, var title:String, var star:String, var review:String, var distance:String, var delivery_fee: String, var time:String? = "")
    :Serializable{
    }
