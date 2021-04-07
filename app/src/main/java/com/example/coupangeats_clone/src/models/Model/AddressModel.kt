package com.example.coupangeats_clone.src.models.Model

import java.io.Serializable

data class AddressModel(val main_address:String, val detail_address:String, val type:String?="기타", val check:Int?=null)
    : Serializable
