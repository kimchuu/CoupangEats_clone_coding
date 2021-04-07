package com.example.coupangeats_clone.src.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coupangeats_clone.databinding.ActivitySearchKeywordBinding
import com.example.template_practice.config.BaseActivity

class SearchKeywordActivity : BaseActivity<ActivitySearchKeywordBinding>(ActivitySearchKeywordBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.searchKeywordBtnBackInToolbar.setOnClickListener {
            finish()
        }

    }
}