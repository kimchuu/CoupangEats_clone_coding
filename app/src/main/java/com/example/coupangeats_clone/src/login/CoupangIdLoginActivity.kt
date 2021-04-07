package com.example.coupangeats_clone.src.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.coupangeats_clone.databinding.ActivityCoupangIdLoginBinding
import com.example.coupangeats_clone.src.main.MainActivity
import com.example.coupangeats_clone.src.models.PostLoginRequest
import com.example.coupangeats_clone.src.models.UserResponse
import com.example.coupangeats_clone.src.register.RegisterActivity
import com.example.template_practice.config.BaseActivity
import com.google.android.material.snackbar.Snackbar



// 쿠팡 아이디로 로그인
class CoupangIdLoginActivity :
    BaseActivity<ActivityCoupangIdLoginBinding>(ActivityCoupangIdLoginBinding::inflate),
    CoupangIdLoginView {

    // 눈 안보이는 상태
    var check = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 버튼 누르면 서버에 로그인 정보 전송
        binding.coupangIdLoginBtnLogin.setOnClickListener {

            val email = binding.coupandIdLoginEtEmail.text.toString()
            val password = binding.coupandIdLoginEtPw.text.toString()
            val postRequest = PostLoginRequest(email, password)

            if(email.equals("")){
                //showCustomToast("아이디를 입력해주세요")
               showSnackBar("아이디를 입력해주세요")
            }
            else if (password.equals("")){
                //showCustomToast("비밀번호를 입력해주세요")
                showSnackBar("비밀번호를 입력해주세요")
            }
            else if (isEmailValid(email) == false){
                showSnackBar("아이디는 이메일 주소 형식으로 입력해주세요")
            }
            else {
                showLoadingDialog(this)
                LoginService(this).tryGetUsers(postRequest)
            }
        }

        // 취소버튼
        binding.coupangIdLoginBtnCancelScreen.setOnClickListener {
            finish()
        }

        // 회원가입 화면 이동
        binding.coupangIdLoginMoveRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        changeEditText()

    }

    // SnackBar 띄우기
    fun showSnackBar(message: String){
        var mSnack = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        var mView = mSnack.view

        mView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        mView.setBackgroundColor(getColor(R.color.error_toast))


        // get textview inside snackbar view
//        val mTextView =
//            mView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
//        // set text to center
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
//            mTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        mSnack.show()
    }

    fun alertDialog(message: String){
        var builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton("확인",DialogInterface.OnClickListener { dialog, which ->

        })

        builder.show()


    }

    fun changeEditText() {

        binding.coupandIdLoginLlEmail.setBackgroundResource(R.drawable.btn_login)
        binding.coupandIdLoginLlPw.setBackgroundResource(R.drawable.btn_login)

        binding.coupandIdLoginEtEmail.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus) {
                binding.coupandIdLoginLlEmail.isPressed = true
            }
            else{
                binding.coupandIdLoginLlEmail.isPressed = false
            }
        }

        binding.coupandIdLoginEtPw.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.coupandIdLoginLlPw.isPressed = true
            }
            else{
                binding.coupandIdLoginLlPw.isPressed = false
            }
        }

        binding.coupandIdLoginEtEmail.doOnTextChanged { text, start, before, count ->
            if (binding.coupandIdLoginEtEmail.toString().count() > 0) {
                binding.coupandIdLoginBtnCancelEmail.visibility = View.VISIBLE
            } else {
                binding.coupandIdLoginBtnCancelEmail.visibility = View.INVISIBLE
            }
        }

        // x 누르면 글자 사라지게
        binding.coupandIdLoginBtnCancelEmail.setOnClickListener {
            binding.coupandIdLoginEtEmail.setText(null)
        }


        // 눈 누르면 글자 보이게
        binding.coupandIdLoginBtnPw.setOnClickListener{
            check = !check
            //binding.coupandIdLoginBtnPw.isPressed = check
            binding.coupandIdLoginBtnPw.isActivated = check
            Log.d(TAG,"CoupangIdLoginActivity - changeEditText() called - ${check}")
            if(check == true){ // 비밀번호 보이게
                binding.coupandIdLoginEtPw.transformationMethod = null
            }
            else{
                binding.coupandIdLoginEtPw.transformationMethod = PasswordTransformationMethod()
            }
        }


    }

    fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    // 회원 정보 가져오기
    override fun onGetUserSuccess(response: UserResponse) {
        dismissLoadingDialog()
        showCustomToast("Get JWT 성공")

        val code = response.code

        // 로그인 성공하면 메인 화면으로
        if (code == 1000) {
            //Log.d(TAG,"CoupangIdLoginActivity - onGetUserSuccess() called - jwt : ${response.result.jwt}")
            sSharedPreferences.edit().putString(X_ACCESS_TOKEN,response.result.jwt).apply()
            //Log.d(TAG,"CoupangIdLoginActivity - sSharedPreferences - jwt : ${sSharedPreferences.getString(X_ACCESS_TOKEN,"왜?")}")
            startActivity(Intent(this, MainActivity::class.java))
        }
        else {
            response.message?.let { alertDialog(it) }
        }

        //showCustomToast("${response.code} : ${response.message}")

    }

    override fun onGetUserFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }



}