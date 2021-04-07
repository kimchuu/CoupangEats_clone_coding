package com.example.coupangeats_clone.src.main.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class DetailRecyclerViewAdapter : RecyclerView.Adapter<DetailRecyclerViewHolder>(){

    private var itemList = ArrayList<MenuModel>()
    //클릭리스너 선언
    private lateinit var itemClickListner: ItemClickListener


    //클릭 인터페이스 정의
    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailRecyclerViewHolder {
        return DetailRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_menu,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: DetailRecyclerViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
        holder.itemView.setOnClickListener {
            itemClickListner.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<MenuModel>){
        this.itemList = list
        notifyDataSetChanged()
    }

    //클릭리스너 등록 매소드
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }
}