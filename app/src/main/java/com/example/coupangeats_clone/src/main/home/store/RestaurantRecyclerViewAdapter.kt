package com.example.coupangeats_clone.src.main.home.Restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.main.home.store.RestaurantRecyclerViewHolder
import com.example.coupangeats_clone.src.models.Model.RestaurantModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class RestaurantRecyclerViewAdapter : RecyclerView.Adapter<RestaurantRecyclerViewHolder>(){

    private var itemList = ArrayList<RestaurantModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantRecyclerViewHolder {
        return RestaurantRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_store,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: RestaurantRecyclerViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<RestaurantModel>){
        this.itemList = list
        notifyDataSetChanged()
    }
}