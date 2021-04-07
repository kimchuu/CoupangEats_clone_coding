package com.example.coupangeats_clone.src.main.orderList

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coupangeats_clone.config.ApplicationClass.Companion.TAG
import com.example.coupangeats_clone.src.main.orderList.onGoing.OnGoingOrderListFragment
import com.example.coupangeats_clone.src.main.orderList.past.PastOrderListFragment

class PagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val fragmentList = ArrayList<Fragment>()
    val titleList = ArrayList<String>()

    fun addFragment(fragment:Fragment, title:String){
        fragmentList.add(fragment)
        notifyDataSetChanged()
        titleList.add(title)
        notifyDataSetChanged()
        Log.d(TAG,"PagerAdapter - addFragment() called - ${fragmentList}")
    }


    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
    }

}