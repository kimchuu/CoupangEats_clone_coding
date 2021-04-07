package com.example.coupangeats_clone.src.main.orderList.onGoing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.OrderModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class OnGoingRecyclerViewAdapter : RecyclerView.Adapter<OnGoingRecyclerViewHolder>(){

    private var itemList = ArrayList<OrderModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnGoingRecyclerViewHolder {
        return OnGoingRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_order_list,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: OnGoingRecyclerViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<OrderModel>){
        this.itemList = list
        notifyDataSetChanged()
    }
}