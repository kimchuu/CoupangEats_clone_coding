package com.example.coupangeats_clone.src.main.detail.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.ActivityDetailStoreBinding
import com.example.coupangeats_clone.databinding.ActivityProductBinding
import com.example.coupangeats_clone.src.models.Model.MenuModel
import com.example.coupangeats_clone.util.MyContext.Companion.context
import com.example.template_practice.config.BaseActivity
import com.google.android.material.appbar.AppBarLayout

class ProductActivity : BaseActivity<ActivityProductBinding>(ActivityProductBinding::inflate) {

    lateinit var menu: MenuModel

    // 수량
    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 데이터 받아오기
        menu = intent.getSerializableExtra("menu") as MenuModel

        Glide.with(context)
            .load(menu.image)
            .into(binding.productImage)

        binding.productTvMenuName.text = menu.title
        binding.productTvMenuPrice.text = menu.price.toString()

        // 스크롤 내리면 top에 메뉴명 보이게
        showAppbar()

        var price = binding.productTvMenuPrice.text

        binding.productBtnPlus.setOnClickListener{
            binding.productBtnPlus.setImageResource(R.drawable.ic_circle_plus_click)
            count++
            binding.productBtnAmount.text = count.toString()+""

            binding.productTvMenuPrice.text = (Integer.parseInt(price.toString()) * count).toString()
        }


        binding.productBtnMinus.setOnClickListener {
            count--
            if (count>1){
                binding.productBtnMinus.setImageResource(R.drawable.ic_circle_minus_click)
            }
            else{
                binding.productBtnMinus.setImageResource(R.drawable.ic_circle_minus)
            }
            binding.productBtnAmount.text = count.toString()+""
            binding.productTvMenuPrice.text = (Integer.parseInt(price.toString()) * count).toString()
        }

        // 카트에 담기
        binding.productGoToCart.setOnClickListener {
            val intent = Intent()
            intent.putExtra("cart",binding.productTvMenuPrice.text)
            setResult(RESULT_OK, intent)
            finish()
        }


    }

    fun showAppbar(){
        var isShow = true
        var scrollRange = -1
        binding.productAppBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                binding.productToolBarLayout.title = menu.title
                isShow = true
            } else if (isShow){
                binding.productToolBarLayout.title = " " //careful there should a space between double quote otherwise it wont work
                isShow = false
            }
        })
    }
}