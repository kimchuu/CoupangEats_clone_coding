package com.example.coupangeats_clone.src.main.detail

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class DetailRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image= itemView.findViewById<ImageView>(R.id.rv_item_menu_image)
    private val title = itemView.findViewById<TextView>(R.id.rv_item_menu_title)
    private val price = itemView.findViewById<TextView>(R.id.rv_item_menu_price)
    private val content = itemView.findViewById<TextView>(R.id.rv_item_menu_content)
    private val banner = itemView.findViewById<LinearLayout>(R.id.recycler_item_menu_ll_banner)

    // 데이터와 뷰 묶기
    fun bindWithView(menuItem: MenuModel){
        // 이미지 설정
        Glide.with(context)
            .load(menuItem.image)
            .into(image)

        title.text = menuItem.title
        price.text = menuItem.price.toString()
        content.text = menuItem.content

        if (menuItem.banner == true){
            banner.visibility = View.VISIBLE
        }
    }
}