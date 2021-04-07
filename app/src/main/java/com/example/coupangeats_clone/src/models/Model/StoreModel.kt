package com.example.coupangeats_clone.src.models.Model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class StoreModel(var image:Int, var image_2:Int?=null,var image_3:Int?=null,var title:String, var star:String, var review:String, var distance:String, var delivery_fee: String, var time:String? = "25-35분", var chita:Int?=0, var least_fee: String?="5000원")
    :Serializable{
    }
