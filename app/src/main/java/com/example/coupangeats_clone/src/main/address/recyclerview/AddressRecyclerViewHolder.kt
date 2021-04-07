package com.example.coupangeats_clone.src.main.address.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.AddressModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class AddressRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val main_address= itemView.findViewById<TextView>(R.id.rv_item_address_tv_main)
    private val detail_address = itemView.findViewById<TextView>(R.id.rv_item_address_tv_detail)
    private val check = itemView.findViewById<ImageView>(R.id.rv_item_address_img_check)

    // 데이터와 뷰 묶기
    fun bindWithView(addressItem: AddressModel){
        main_address.text = addressItem.main_address
        detail_address.text = addressItem.detail_address
        Glide.with(context)
            .load(addressItem.check)
            .into(check)
    }
}