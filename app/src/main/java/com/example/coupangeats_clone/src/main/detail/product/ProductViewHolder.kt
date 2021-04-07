package com.example.coupangeats_clone.src.main.detail.product

import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image= itemView.findViewById<ImageView>(R.id.rv_item_menu_image)
    private val title = itemView.findViewById<TextView>(R.id.rv_item_menu_title)
    private val price = itemView.findViewById<TextView>(R.id.rv_item_menu_price)
    private val content = itemView.findViewById<TextView>(R.id.rv_item_menu_content)

    private val button = itemView.findViewById<RadioButton>(R.id.menu_select_radio_btn)
    private val product = itemView.findViewById<RadioButton>(R.id.menu_select_tv)

    // 데이터와 뷰 묶기
    fun bindWithView(menuItem: MenuModel){
        //product =
    }
}