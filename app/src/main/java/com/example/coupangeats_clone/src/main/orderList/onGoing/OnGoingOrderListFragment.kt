package com.example.coupangeats_clone.src.main.orderList.onGoing

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.FragmentOrderListOngoingBinding
import com.example.coupangeats_clone.src.models.Model.OrderModel
import com.example.template_practice.config.BaseFragment

class OnGoingOrderListFragment: BaseFragment<FragmentOrderListOngoingBinding>(FragmentOrderListOngoingBinding::bind, R.layout.fragment_order_list_ongoing) {

    // data list
    var orderList = ArrayList<OrderModel>()
    // orderlist adapter
    lateinit var orderListAdapter: OnGoingRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList(orderList)
        orderListAdapter = OnGoingRecyclerViewAdapter()
        orderListAdapter.submitList(orderList)
        binding.orderListOnGoingRv.layoutManager = LinearLayoutManager(context)
        binding.orderListOnGoingRv.adapter = orderListAdapter

    }

    fun dataList(list:ArrayList<OrderModel>){
        list.add(OrderModel(R.drawable.detail_macaron,"달달한날","2021-03-25 19:22","준비중",2,"마카롱",12300))
    }
}