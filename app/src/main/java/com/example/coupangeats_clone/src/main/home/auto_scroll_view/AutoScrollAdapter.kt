package com.example.coupangeats_clone.src.main.home.auto_scroll_view

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R

class AutoScrollAdapter(val context:Context, val data: ArrayList<Int>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //super.instantiateItem(container, position)

        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.auto_viewpager,null)
        val image_container = view.findViewById<ImageView>(R.id.image_container)
        Glide.with(context).load(data.get(position)).into(image_container)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        //super.destroyItem(container, position, `object`)
        container.removeView(`object`as View)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }
}