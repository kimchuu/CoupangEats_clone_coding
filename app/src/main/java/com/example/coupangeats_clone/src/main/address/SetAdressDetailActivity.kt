package com.example.coupangeats_clone.src.main.address

import android.content.Intent
import android.os.Bundle
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.coupangeats_clone.databinding.ActivitySetAddressDetailBinding
import com.example.coupangeats_clone.src.main.MainActivity
import com.example.coupangeats_clone.src.main.address.api.PostAddressActivityView
import com.example.coupangeats_clone.src.main.address.api.PostAddressService
import com.example.coupangeats_clone.src.models.AddressRequest
import com.example.coupangeats_clone.src.models.AddressResponse
import com.example.coupangeats_clone.src.models.Model.AddressModel
import com.example.template_practice.config.BaseActivity

class SetAdressDetailActivity :
    BaseActivity<ActivitySetAddressDetailBinding>(ActivitySetAddressDetailBinding::inflate)
, PostAddressActivityView {

    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setAddressDetailBtnBackInToolbar.setOnClickListener {
            finish()
        }

        binding.setAddressDetailBtn1.setOnClickListener {
            type = "집"
            binding.setAddressDetailBtn1.setBackgroundColor(resources.getColor(R.color.main_color))
        }
        binding.setAddressDetailBtn2.setOnClickListener {
            type = "회사"
            binding.setAddressDetailBtn2.setBackgroundColor(resources.getColor(R.color.main_color))
        }
        binding.setAddressDetailBtn3.setOnClickListener {
            type = "기타"
            binding.setAddressDetailBtn3.setBackgroundColor(resources.getColor(R.color.main_color))
        }



        binding.setAddressDetailBtnFinish.setOnClickListener {

            val jwt = sSharedPreferences.getString(X_ACCESS_TOKEN,"")

            val base_address = binding.setAddressDetailEtBase.text.toString()
            val detail_address = binding.setAddressDetailEtDetail.text.toString()
            val addressRequest = AddressRequest(base_address,detail_address,type)


           // PostAddressService(this).tryPostAddress(jwt!!, addressRequest)


            val add_address = AddressModel(base_address, detail_address, type)
            val intent = Intent()
            intent.putExtra("add_address", add_address);
            setResult(RESULT_OK,intent)
            finish()

        }
    }

    override fun onPostAddressSuccess(response: AddressResponse) {
        showCustomToast(response.message!!)
        if (response.code == 1000) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onPostAddressFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}