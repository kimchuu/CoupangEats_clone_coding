package com.example.coupangeats_clone.src.models.Model

import java.io.Serializable

data class MenuModel(var image:Int?=null, var title:String, var price:Int, var content:String="", var banner:Boolean?=false)
    :Serializable
