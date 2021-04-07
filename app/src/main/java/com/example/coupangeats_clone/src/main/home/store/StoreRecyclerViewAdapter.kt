package com.example.coupangeats_clone.src.main.home.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.main.home.small_store.SmallStoreRecyclerViewAdapter
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class StoreRecyclerViewAdapter : RecyclerView.Adapter<StoreRecyclerViewHolder>(){

    private var itemList = ArrayList<StoreModel>()

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener:ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreRecyclerViewHolder {
        return StoreRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_store,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: StoreRecyclerViewHolder, position: Int) {
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