package com.example.coupangeats_clone.src.main.myEats

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.FragmentMyEatsBinding
import com.example.coupangeats_clone.src.main.myEats.api.GetUserService
import com.example.coupangeats_clone.src.main.myEats.api.MyEatsUserView
import com.example.coupangeats_clone.src.models.Model.MyEatsListModel
import com.example.coupangeats_clone.src.models.UserInfoResponse
import com.example.template_practice.config.BaseFragment

class MyEatsFragment : BaseFragment<FragmentMyEatsBinding>(FragmentMyEatsBinding::bind, R.layout.fragment_my_eats)
, MyEatsUserView{

    // my 이츠 list
    var myEatsList = ArrayList<MyEatsListModel>()
    // my 이츠 list Adapter
    lateinit var listAdapter: MyEatsRecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //회원 정보 받아오기
        GetUserService(this).tryGetUsers()

        // list data 넣기
        addDataList()

        // 인스턴스 생성
        listAdapter = MyEatsRecyclerViewAdapter()
        listAdapter.submitList(myEatsList)
        binding.myEatsRecyclerViewList.layoutManager = LinearLayoutManager(context)
        binding.myEatsRecyclerViewList.adapter = listAdapter
    }

    fun addDataList(){
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_address_list,"배달 주소 관리"))
        myEatsList.add(MyEatsListModel(R.drawable.ic_iconfinder_heart,"즐겨찾기"))
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_tag,"할인쿠폰"))
        myEatsList.add(MyEatsListModel(R.drawable.ic_my_eats_card,"결제관리"))
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_deliver,"배달파트너 모집",R.drawable.my_eats_new))
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_question,"자주 묻는 질문"))
        myEatsList.add(MyEatsListModel(R.drawable.ic_my_eats_phone,"고객 지원"))
        myEatsList.add(MyEatsListModel(R.drawable.ic_my_eats_setting,"설정"))
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_notice,"공지사항"))
        myEatsList.add(MyEatsListModel(R.drawable.my_eats_list,"약관·개인정보 처리방침"))
    }

    override fun onGetUserSuccess(response: UserInfoResponse) {

        if (response.code==1000){
            binding.myEatsToolbarTitle.text = response.result.phoneNumber
            binding.myEatsToolbarPhoneNumber.text = response.result.name
        }
        else{
            response.message?.let { showCustomToast(it) }
        }
    }

    override fun onGetUserFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}