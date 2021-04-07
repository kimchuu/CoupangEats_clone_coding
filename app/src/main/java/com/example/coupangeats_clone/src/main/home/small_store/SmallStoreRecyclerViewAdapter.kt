package com.example.coupangeats_clone.src.main.home.small_store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class SmallStoreRecyclerViewAdapter : RecyclerView.Adapter<SmallStoreRecyclerViewHolder>(){

    private var itemList = ArrayList<StoreModel>()

    interface ItemClickListener{
        fun onClick(view: View,position: Int)
    }

    private lateinit var itemClickListener:ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallStoreRecyclerViewHolder {
        return SmallStoreRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_store_small,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: SmallStoreRecyclerViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<StoreModel>){
        this.itemList = list
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

}