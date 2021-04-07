package com.example.coupangeats_clone.src.main.orderList.past

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.OrderModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class PastRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // 뷰 가져오기
    private val image = itemView.findViewById<ImageView>(R.id.rv_item_order_list_image)
    private val title = itemView.findViewById<TextView>(R.id.rv_item_order_list_title)
    private val time = itemView.findViewById<TextView>(R.id.rv_item_order_list_time)
    private val delivery_state = itemView.findViewById<TextView>(R.id.rv_item_order_list_delivery_state)
    private var amount = itemView.findViewById<TextView>(R.id.rv_item_order_list_amount)
    private var menu = itemView.findViewById<TextView>(R.id.rv_item_order_list_tv_menu)
    private val price = itemView.findViewById<TextView>(R.id.rv_item_order_list_tv_price)

    // 데이터와 뷰 묶기
    fun bindWithView(orderItem: OrderModel){
        // 이미지 설정
        Glide.with(context)
            .load(orderItem.image)
            .placeholder(R.drawable.app_icon)
            .into(image)

        title.text = orderItem.title
        time.text = orderItem.time
        delivery_state.text = orderItem.delivery_state
        amount.text = orderItem.amount.toString()
        menu.text = orderItem.menu
        price.text = orderItem.price.toString()
    }
}