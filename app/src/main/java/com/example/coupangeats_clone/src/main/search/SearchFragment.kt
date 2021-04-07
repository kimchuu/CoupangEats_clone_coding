package com.example.coupangeats_clone.src.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.FragmentSearchBinding
import com.example.coupangeats_clone.src.models.Model.CategoryModel
import com.example.template_practice.config.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {

    // category list
    var categorylist = ArrayList<CategoryModel>()
    // category list Adapter
    lateinit var categoryAdapter: SearchCategoryRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 카테고리 사진 리스트에 넣기
        addCategoryList()

        // category adapter 인스턴스 생성
        categoryAdapter = SearchCategoryRecyclerViewAdapter()
        categoryAdapter.submitList(categorylist)
        binding.searchRecyclerViewCategory.layoutManager = GridLayoutManager(context,2)
        binding.searchRecyclerViewCategory.adapter =categoryAdapter

        binding.searchEt.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                startActivity(Intent(context,SearchKeywordActivity::class.java))
            }
        }

    }

    fun addCategoryList(){
        categorylist.add(CategoryModel(R.drawable.category_new, "신규 맛집"))
        categorylist.add(CategoryModel(R.drawable.category_one,"1인분"))
        categorylist.add(CategoryModel(R.drawable.category_korean,"한식"))
        categorylist.add(CategoryModel(R.drawable.category_chicken,"치킨"))
        categorylist.add(CategoryModel(R.drawable.category_tteokbokki,"분식"))
        categorylist.add(CategoryModel(R.drawable.category_pork_cutlet,"돈까스"))
        categorylist.add(CategoryModel(R.drawable.category_jokbal,"족발/보쌈"))
        categorylist.add(CategoryModel(R.drawable.category_ttang,"찜/탕"))
        categorylist.add(CategoryModel(R.drawable.category_roast,"구이"))
        categorylist.add(CategoryModel(R.drawable.category_pizza,"피자"))
        categorylist.add(CategoryModel(R.drawable.category_chinese,"중식"))
        categorylist.add(CategoryModel(R.drawable.category_japanese,"일식"))
        categorylist.add(CategoryModel(R.drawable.category_sushi,"회/해물"))
        categorylist.add(CategoryModel(R.drawable.category_pasta,"양식"))
        categorylist.add(CategoryModel(R.drawable.category_coffee,"커피/차"))
        categorylist.add(CategoryModel(R.drawable.category_dessert,"디저트"))
        categorylist.add(CategoryModel(R.drawable.category_snack,"간식"))
        categorylist.add(CategoryModel(R.drawable.category_assian,"아시안"))
        categorylist.add(CategoryModel(R.drawable.category_sandwich,"샌드위치"))
        categorylist.add(CategoryModel(R.drawable.category_salad,"샐러드"))
        categorylist.add(CategoryModel(R.drawable.category_burger,"버거"))
        categorylist.add(CategoryModel(R.drawable.category_mexican,"멕시칸"))
        categorylist.add(CategoryModel(R.drawable.category_lunch_box,"도시락"))
        categorylist.add(CategoryModel(R.drawable.category_jook,"죽"))
        categorylist.add(CategoryModel(R.drawable.category_franchise,"프랜차이즈"))

    }
}