package com.example.coupangeats_clone.src.main.favorite

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.ActivityFavoriteBinding
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.template_practice.config.BaseActivity
import okhttp3.internal.notify

class FavoriteActivity :
    BaseActivity<ActivityFavoriteBinding>(ActivityFavoriteBinding::inflate) {

    // 즐겨찾기 list
    var favoriteList = ArrayList<StoreModel>()
    // 즐겨찾기 list Adapter
    lateinit var favoriteAdapter: FavoriteRecyclerViewAdapter

    lateinit var favorite : StoreModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.favoriteSearchBtnBack.setOnClickListener {
            finish()
        }

        // 즐겨찾기 리스트
        addFavoriteList()

        favoriteAdapter = FavoriteRecyclerViewAdapter()
        favoriteAdapter.submitList(favoriteList)
        binding.favoriteRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.favoriteRecyclerview.adapter = favoriteAdapter



        // 즐겨찾기 추가된 항목 받아오기
        if (intent.getSerializableExtra("favorite") !=null) {
            favorite = intent.getSerializableExtra("favorite") as StoreModel
            favoriteList.add(favorite)
        }

        binding.favoriteTvCount.text = favoriteAdapter.itemCount.toString()


    }


    fun addFavoriteList() {
        favoriteList.add(
            StoreModel(
                R.drawable.category_chicken,
                title = "굽네치킨 신정2동점",
                star = "4.6",
                review = "(211)",
                distance = "1.8km",
                delivery_fee = "무료배달",
                time = "30-40분"
            )
        )
        favoriteList.add(
            StoreModel(
                R.drawable.category_burger,
                title = "버거킹 신정네거리점",
                star = "4.6",
                review = "(1,389)",
                distance = "0.7km",
                delivery_fee = "무료배달",
                time = "27-37분"
            )
        )
        favoriteList.add(
            StoreModel(
                R.drawable.category_tteokbokki,
                title = "몬스터 곱창떡볶이 강서화곡점",
                star = "5.0",
                review = "(10)",
                distance = "3.5km",
                delivery_fee = "3,000원",
                time = "13-23분"
            )
        )
    }

}