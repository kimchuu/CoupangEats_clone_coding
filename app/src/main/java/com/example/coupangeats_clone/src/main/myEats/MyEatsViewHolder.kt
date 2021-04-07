package com.example.coupangeats_clone.src.main.myEats

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.MyEatsListModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class MyEatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image = itemView.findViewById<ImageView>(R.id.rv_item_my_eats_image)
    private val title = itemView.findViewById<TextView>(R.id.rv_item_my_eats_tv_title)
    private val new = itemView.findViewById<ImageView>(R.id.rv_item_my_eats_img_new)

    // 데이터와 뷰 묶기
    fun bindWithView(myEatsItem : MyEatsListModel){
        // 이미지 설정
        Glide.with(context)
            .load(myEatsItem.image)
            .placeholder(R.drawable.ic_iconfinder_heart)
            .into(image)
        title.text = myEatsItem.title

        Glide.with(context)
            .load(myEatsItem.isNew)
            .into(new)
    }
}