package com.example.coupangeats_clone.src.main.orderList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass.Companion.TAG
import com.example.coupangeats_clone.databinding.FragmentOrderListBinding
import com.example.coupangeats_clone.src.main.orderList.onGoing.OnGoingOrderListFragment
import com.example.coupangeats_clone.src.main.orderList.past.PastOrderListFragment
import com.example.template_practice.config.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OrderListFragment : BaseFragment<FragmentOrderListBinding>(FragmentOrderListBinding::bind, R.layout.fragment_order_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        //val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
//
//        val pagerAdapter = PagerAdapter(childFragmentManager)
//
//        // 2개의 fragment add
//        pagerAdapter.addFragment(PastOrderListFragment())
//        pagerAdapter.addFragment(OnGoingOrderListFragment())
//        // Adapter 연결
//        binding.orderListViewPager.adapter = pagerAdapter
//
////        binding.orderListViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
////            override fun onPageSelected(position: Int) {
////                super.onPageSelected(position)
////                Log.e("ViewPagerFragment", "Page ${position+1}")
////            }
////        })
//        binding.orderListViewPager.setCurrentItem(0)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = super.onCreateView(inflater, container, savedInstanceState)


        binding.orderListTabLayout.setupWithViewPager(binding.orderListViewPager)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpViewPager()

        Log.d(TAG,"OrderListFragment - onActivityCreated() called - ${binding.orderListViewPager.adapter!!.count}")

        binding.orderListTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG,"OrderListFragment - onTabSelected() called - tab position : ${tab!!.position}")
                //Log.d(TAG,"OrderListFragment - onTabSelected() called - item position : ${binding.orderListViewPager.adapter}")
                if(tab!=null){
                    binding.orderListViewPager.setCurrentItem(tab.position)
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    fun setUpViewPager(){
        var pagerAdapter = PagerAdapter(childFragmentManager)

        // 2개의 fragment add
        pagerAdapter.addFragment(PastOrderListFragment(), "과거내역조회")
        pagerAdapter.addFragment(OnGoingOrderListFragment(),"준비중")
        // Adapter 연결
        binding.orderListViewPager.adapter = pagerAdapter
    }
}