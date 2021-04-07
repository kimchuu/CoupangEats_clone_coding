package com.example.coupangeats_clone.src.main.detail

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.ActivityDetailStoreBinding
import com.example.coupangeats_clone.src.main.detail.product.ProductActivity
import com.example.coupangeats_clone.src.main.favorite.FavoriteActivity
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.src.models.Model.StoreModel
import com.example.coupangeats_clone.util.MyContext
import com.example.coupangeats_clone.util.MyContext.Companion.context
import com.example.template_practice.config.BaseActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar


class DetailStoreActivity : BaseActivity<ActivityDetailStoreBinding>(ActivityDetailStoreBinding::inflate){

    var menuList = ArrayList<MenuModel>()

    lateinit var menuAdapter: DetailRecyclerViewAdapter

    lateinit var storelist:StoreModel

    val CART = 1000

    var total_price : Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showAppbar()

        addDataList(menuList)
        menuAdapter = DetailRecyclerViewAdapter()
        menuAdapter.submitList(menuList)
        binding.detailRvMenuList.layoutManager = LinearLayoutManager(this)
        binding.detailRvMenuList.adapter = menuAdapter


        // 데이터 받아오기
        if ( intent.getSerializableExtra("content") !=null) {
            storelist = intent.getSerializableExtra("content") as StoreModel

            Glide.with(context)
                .load(storelist.image)
                .into(binding.detailStoreImage)
            binding.detailStoreStoreName.text = storelist.title
            binding.detailStoreStar.text = storelist.star
            binding.detailStoreReviewAmount.text = storelist.review
            binding.detailStoreDeliveryFee.text = storelist.delivery_fee
            binding.detailStoreStoreTime.text = storelist.time
            binding.detailStoreTvLeastFee.text = storelist.least_fee
            Glide.with(context)
                .load(storelist.chita)
                .into(binding.detailStoreChita)
        }


        // recycler view click event
        menuAdapter.setItemClickListener(object :DetailRecyclerViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                //showCustomToast(""+position)
                val intent = Intent(context, ProductActivity::class.java)
                intent.putExtra("menu", menuList[position])
                startActivityForResult(intent,CART)
            }
        })

        binding.detailToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId){
                R.id.like -> {
                    val intent = Intent(context, FavoriteActivity::class.java)
                    intent.putExtra("favorite",storelist)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CART){
            if (resultCode == RESULT_OK){
                var price = data?.getStringExtra("cart")
                total_price += Integer.parseInt(price)
                binding.detailStoreRlCart.visibility = View.VISIBLE
                Snackbar.make(binding.root,"카트에 담겼습니다",Snackbar.LENGTH_SHORT)
            }
        }
        binding.detailCartSeeTvPrice.text = total_price.toString()
    }

    fun addDataList(list : ArrayList<MenuModel>){
        list.add(MenuModel(R.drawable.menu_image1,"마카롱",2300,banner = true))
        list.add(MenuModel(R.drawable.menu_image2,"머랭쿠키",2000,banner = true))
        list.add(MenuModel(title = "미니당근케이크",price = 3500,content = "쉬폰처럼 부드러운 미니당근케이크"))
        list.add(MenuModel(null,"꼬끄후레이크",3500,content = "꼬끄를 한번더 바삭하게 구워서 과자처럼 또는 요거트나 우유에 얺어드시면 더욱 맛있습니다"))
        list.add(MenuModel(R.drawable.menu_image3,"수제밀크티",5000,))
        list.add(MenuModel(null,"마카롱세트",13000,"(상자포장+쇼핑백)"))
    }

    fun showAppbar(){
        var isShow = true
        var scrollRange = -1
        binding.detailAppBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.detailToolBarLayout.title = storelist.title
                isShow = true
            } else if (isShow){
                binding.detailToolBarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })
    }

}