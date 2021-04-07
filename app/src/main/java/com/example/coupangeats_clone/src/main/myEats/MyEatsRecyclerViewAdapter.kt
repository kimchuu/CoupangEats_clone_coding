package com.example.coupangeats_clone.src.main.myEats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.MyEatsListModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class MyEatsRecyclerViewAdapter : RecyclerView.Adapter<MyEatsViewHolder>(){

    private var itemList = ArrayList<MyEatsListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEatsViewHolder {
        return MyEatsViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_my_eats_list,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: MyEatsViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<MyEatsListModel>){
        this.itemList = list
        notifyDataSetChanged()
    }


}