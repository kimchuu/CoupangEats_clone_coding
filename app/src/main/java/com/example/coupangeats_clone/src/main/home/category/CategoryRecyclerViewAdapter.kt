package com.example.coupangeats_clone.src.main.home.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.src.models.Model.CategoryModel
import com.example.coupangeats_clone.util.MyContext.Companion.context

class CategoryRecyclerViewAdapter : RecyclerView.Adapter<CategoryViewHolder>(){

    private var itemList = ArrayList<CategoryModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_item_category,parent,false))
    }

    // 뷰가 bind되면 데이터 뷰 홀더에 넘겨준다
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindWithView(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 외부에서 어댑터에 배열 넣어주기
    fun submitList(list:ArrayList<CategoryModel>){
        this.itemList = list
        notifyDataSetChanged()
    }


}