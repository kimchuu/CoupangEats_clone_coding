package com.example.coupangeats_clone.src.main.address

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coupangeats_clone.databinding.ActivitySetAddressBinding
import com.example.coupangeats_clone.src.main.address.api.PatchAddressView
import com.example.coupangeats_clone.src.main.address.recyclerview.AddressRecyclerViewAdapter
import com.example.coupangeats_clone.src.models.AddressResponse
import com.example.coupangeats_clone.src.models.Model.AddressModel
import com.example.coupangeats_clone.util.MyContext.Companion.context
import com.example.template_practice.config.BaseActivity


class SetAddressActivity:BaseActivity<ActivitySetAddressBinding>(ActivitySetAddressBinding::inflate)
, PatchAddressView{

    var addressList = ArrayList<AddressModel>()
    lateinit var addressAdapter : AddressRecyclerViewAdapter

    var ADD:Int = 2

    var RESULT_OTHER = 100

    companion object {
        var check = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 주소 추가
        binding.setAddressSearch.setOnClickListener{
            val intent = Intent(this, SetAdressDetailActivity::class.java)
            startActivityForResult(intent, ADD)
        }

        binding.setAddressBtnCancelInToolbar.setOnClickListener {
            finish()
        }

        // address list 어댑터에 연결
        addDataList()
        addressAdapter = AddressRecyclerViewAdapter()
        addressAdapter.submitList(addressList)
        binding.setAddressRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.setAddressRecyclerView.adapter = addressAdapter


        checkWhat()

        binding.setAddressBtnHome.setOnClickListener {
            check = 1
            checkWhat()
            //PatchAddressService(this).tryPatchAddress(sSharedPreferences.getInt("user_idx",0),check)
            val intent = Intent()
            intent.putExtra("address", "집");
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.setAddressBtnCompany.setOnClickListener {
            check =2
            checkWhat()
            //PatchAddressService(this).tryPatchAddress(sSharedPreferences.getInt("user_idx",0),check)
            val intent = Intent()
            intent.putExtra("address", "회사");
            setResult(RESULT_OK, intent)
            finish()
        }

        // 기타 주소 click listener
        addressAdapter.setItemClickListener(object:AddressRecyclerViewAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int) {
                showCustomToast(""+position)
            }
        })

       

    }

    // 주소지 추가
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD){
            if (resultCode == RESULT_OK){
                var item = data?.getSerializableExtra("add_address") as AddressModel
                addressList.add(item)
                addressAdapter.notifyDataSetChanged()
            }
        }
    }

    fun addDataList(){
        addressList.add(AddressModel("소프트스퀘어드", "서울특별시 금천구 가산디지털1로 128"))
        addressList.add(AddressModel("시작 스터디카페", "서울특별시 양천구 중앙로 247"))

    }

    fun checkWhat(){
        Log.d(TAG, "SetAddressActivity - checkWhat() called - ${check}")
        if (check == 1){
            binding.setAddressImgCheckHome.visibility = View.VISIBLE
            binding.setAddressImgCheckCompany.visibility = View.INVISIBLE
        }
        else if (check ==2 ){
            binding.setAddressImgCheckCompany.visibility = View.VISIBLE
            binding.setAddressImgCheckHome.visibility = View.INVISIBLE
        }
        else{
            binding.setAddressImgCheckCompany.visibility = View.INVISIBLE
            binding.setAddressImgCheckHome.visibility = View.INVISIBLE
        }
    }

    override fun onPatchAddressSuccess(response: AddressResponse) {
        if (response.code == 1000){
            //startActivity(Intent(this,MainActivity::class.java))
        }
        else{
            //showCustomToast(response.message!!)
        }
    }

    override fun onPatchAddressFailure(message: String) {
        //showCustomToast(message)
    }
}