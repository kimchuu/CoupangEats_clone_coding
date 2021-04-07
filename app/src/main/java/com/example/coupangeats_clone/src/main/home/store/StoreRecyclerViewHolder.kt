package com.example.coupangeats_clone.src.main.home.store

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class StoreRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image_1 = itemView.findViewById<ImageView>(R.id.ry_item_store_img_1)
    private val image_2 = itemView.findViewById<ImageView>(R.id.ry_item_store_img_2)
    private val image_3 = itemView.findViewById<ImageView>(R.id.ry_item_store_img_3)
    private val title = itemView.findViewById<TextView>(R.id.ry_item_store_title)
    private val star = itemView.findViewById<TextView>(R.id.ry_item_store_info_star)
    private val review = itemView.findViewById<TextView>(R.id.ry_item_store_info_review)
    private val dist = itemView.findViewById<TextView>(R.id.ry_item_store_info_distance)
    private val delivery_fee = itemView.findViewById<TextView>(R.id.ry_item_store_info_delivery_fee)
    private val time = itemView.findViewById<TextView>(R.id.ry_item_store_info_time)
    private val chita = itemView.findViewById<ImageView>(R.id.ry_item_store_img_chita)

    // 데이터와 뷰 묶기
    fun bindWithView(categoryItem: StoreModel){
        // 이미지 설정
        Glide.with(context)
            .load(categoryItem.image)
            .placeholder(R.drawable.app_icon)
            .into(image_1)
        Glide.with(context)
            .load(categoryItem.image_2)
            .placeholder(R.drawable.app_icon)
            .into(image_2)
        Glide.with(context)
            .load(categoryItem.image_3)
            .placeholder(R.drawable.app_icon)
            .into(image_3)

        title.text = categoryItem.title
        star.text =categoryItem.star
        review.text = categoryItem.review
        dist.text = categoryItem.distance
        delivery_fee.text = categoryItem.delivery_fee
        time.text = categoryItem.time

        Glide.with(context)
            .load(categoryItem.chita)
            .into(chita)
    }
}