package com.example.coupangeats_clone.src.main.search

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.CategoryModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class SearchCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image = itemView.findViewById<ImageView>(R.id.ry_item_search_image)
    private val title = itemView.findViewById<TextView>(R.id.ry_item_search_title)

    // 데이터와 뷰 묶기
    fun bindWithView(categoryItem : CategoryModel){
        // 이미지 설정
        Glide.with(context)
            .load(categoryItem.image)
            .placeholder(R.drawable.ic_arrow_right_circle)
            .into(image)
        title.text = categoryItem.title
    }
}