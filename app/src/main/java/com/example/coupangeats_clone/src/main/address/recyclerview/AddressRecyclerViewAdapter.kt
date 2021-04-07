package com.example.coupangeats_clone.src.main.address.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.RecyclerItemAddressBinding
import com.example.coupangeats_clone.src.main.home.small_store.SmallStoreRecyclerViewAdapter
import com.example.coupangeats_clone.src.models.Model.AddressModel
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class AddressRecyclerViewAdapter() : RecyclerView.Adapter<AddressRecyclerViewHolder>(){

    private var itemList = ArrayList<AddressModel>()

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressRecyclerViewHolder {
        return AddressRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_address,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: AddressRecyclerViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<AddressModel>){
        this.itemList = list
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

}