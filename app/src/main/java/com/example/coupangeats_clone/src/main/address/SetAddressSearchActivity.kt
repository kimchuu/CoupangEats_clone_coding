package com.example.coupangeats_clone.src.main.address

import android.content.Intent
import android.os.Bundle
import com.example.coupangeats_clone.databinding.ActivitySetAddressSearchBinding
import com.example.template_practice.config.BaseActivity

class SetAddressSearchActivity:BaseActivity<ActivitySetAddressSearchBinding>(ActivitySetAddressSearchBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setAddressSearchBtnBackInToolbar.setOnClickListener {
            finish()
        }

        binding.setAddressSearchBtn.setOnClickListener{
            startActivity(Intent(this, SetAdressDetailActivity::class.java))
        }
    }
}