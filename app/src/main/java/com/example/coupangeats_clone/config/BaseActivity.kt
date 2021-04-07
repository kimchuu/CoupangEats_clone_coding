package com.example.template_practice.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.template_practice.util.LoadingDialog

open class BaseActivity<B: ViewBinding>(private val inflate: (LayoutInflater) -> B)
    :AppCompatActivity(){
        protected lateinit var binding:B
        private set
    lateinit var mLoadingDialog:LoadingDialog

    companion object{
        val TAG: String = "로그"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 로딩 다이얼로그, 로딩창 띄워줌
    fun showLoadingDialog(context: Context){
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    fun dismissLoadingDialog() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
    }

    // Toast 쉽게 띄울 수 있게
    fun showCustomToast(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}