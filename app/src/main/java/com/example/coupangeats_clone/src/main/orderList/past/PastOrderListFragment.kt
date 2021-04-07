package com.example.coupangeats_clone.src.main.orderList.past

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.FragmentOrderListPastBinding
import com.example.coupangeats_clone.src.models.Model.OrderModel
import com.example.template_practice.config.BaseFragment

class PastOrderListFragment: BaseFragment<FragmentOrderListPastBinding>(FragmentOrderListPastBinding::bind, R.layout.fragment_order_list_past) {

    // data list
    var orderList = ArrayList<OrderModel>()
    // orderlist adapter
    lateinit var orderListAdapter: PastRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList(orderList)
        orderListAdapter = PastRecyclerViewAdapter()
        orderListAdapter.submitList(orderList)
        binding.orderListPastRv.layoutManager = LinearLayoutManager(context)
        binding.orderListPastRv.adapter = orderListAdapter
    }

    fun dataList(list:ArrayList<OrderModel>){
        list.add(OrderModel(R.drawable.category_chicken,"굽네치킨 신정2동점","2021-03-23 19:18","배달완료",1,"고추 바사삭 한마리",17000))
        list.add(OrderModel(R.drawable.detail_macaron,"달달한날","2021-03-25 18:29","배달완료",2,"마카롱",4600))
    }
}