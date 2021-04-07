package com.example.coupangeats_clone.src.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.*
import android.view.inputmethod.InputMethodManager
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.coupangeats_clone.databinding.ActivityRegisterBinding
import com.example.coupangeats_clone.src.main.MainActivity
import com.example.coupangeats_clone.src.models.PostSignUpRequest
import com.example.coupangeats_clone.src.models.SignUpResponse
import com.example.template_practice.config.BaseActivity
import java.util.*


class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate),
    RegisterActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG: String = "로그"
        super.onCreate(savedInstanceState)

        // 모두 동의 check box
        var check = false

        // 이메일 형식 확인
        binding.registerEtEmail.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    // 이메일 빈칸이면
                    if (binding.registerEtEmail.text == null) {
                        binding.registerHelperTextEmail.text = "이메일을 입력하세요"
                        binding.registerHelperTextEmail.visibility = View.VISIBLE
                        binding.registerRlEditEmail.isActivated =true
                    }
                    // 이메일 형식 맞으면
                    if (isEmailValid(binding.registerEtEmail.text)) {
                        binding.registerCheckEmail.visibility = View.VISIBLE
                        binding.registerHelperTextEmail.visibility = View.GONE
                        binding.registerRlEditEmail.isActivated =false
                    }
                    else { // 이메일 형식이 아니면
                        binding.registerCheckEmail.visibility = View.INVISIBLE
                        binding.registerHelperTextEmail.visibility = View.VISIBLE
                        binding.registerHelperTextEmail.text = "이메일을 올바르게 입력하세요"
                        binding.registerRlEditEmail.isActivated =true
                    }
                }
            }

        // 비밀번호
        binding.registerEtPw.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (binding.registerEtPw.text.length == null) {
                        binding.registerTvPrecautionPassword.visibility = GONE
                        binding.registerHelperTextPassword.visibility = VISIBLE
                        binding.registerRlEditPassword.isActivated =true
                    }

                    if (binding.registerEtPw.text.length < 6 || binding.registerEtPw.text.length > 15) {
                        binding.registerTvPrecautionPassword.visibility = GONE
                        binding.registerCheckPw.visibility = View.INVISIBLE
                        binding.registerHelperTextPassword.visibility = VISIBLE
                        binding.registerRlEditPassword.isActivated =true
                    }
                    // 제대로 입력했을경우
                    else {
                        binding.registerHelperTextPassword.visibility = View.GONE
                        binding.registerCheckPw.visibility = View.VISIBLE
                        binding.registerTvPrecautionPassword.visibility = GONE
                        binding.registerRlEditPassword.isActivated =false
                    }
                }
            }

        //이름
        binding.registerEtName.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (binding.registerEtName.text.length < 2) {
                        binding.registerCheckName.visibility = View.INVISIBLE
                        binding.registerHelperTextName.visibility = View.VISIBLE
                        binding.registerRlEditUserName.isActivated =true
                    }
                    else { // 제대로 입력
                        binding.registerHelperTextName.visibility = View.GONE
                        binding.registerCheckName.visibility = View.VISIBLE
                        binding.registerRlEditUserName.isActivated = false
                    }
                }
            }

        //핸드폰 번호

        val etPhone = binding.registerEtPhoneNumber

        // XXX-XXX-XXX 번호 포맷으로
        //etPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        // 완료 누르면 커서 사라지게
        etPhone.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
                etPhone.setFocusable(false)
                etPhone.setFocusableInTouchMode(true)
                true
            } else {
                false
            }
        }

        // 맞는 번호인지 확인
        etPhone.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    if (etPhone.text.length == 11) { // 제대로 입력
                        binding.registerHelperTextPhoneNumber.visibility = View.GONE
                        binding.registerCheckPhoneNumber.visibility = View.VISIBLE
                        binding.registerRlEditPhoneNumber.isActivated = false
                    }
                    else {
                        binding.registerHelperTextPhoneNumber.text = "휴대폰 번호를 올바르게 입력해주세요."
                        binding.registerHelperTextPhoneNumber.visibility = View.VISIBLE
                        binding.registerCheckPhoneNumber.visibility = View.INVISIBLE
                        binding.registerRlEditPhoneNumber.isActivated =true
                    }
                }
            }


        // 모두 동의
        binding.registerCheckboxAllAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            check = !check
            binding.registerCheckboxAgree1.isChecked = check
            binding.registerCheckboxAgree2.isChecked = check
            binding.registerCheckboxAgree3.isChecked = check
            binding.registerCheckboxAgree4.isChecked = check
            binding.registerCheckboxAgree5.isChecked = check
        }

//


        // 버튼 누르면 서버에 회원가입 정보 전송
        binding.registerBtnRegister.setOnClickListener {

            val email = binding.registerEtEmail.text.toString()
            val password = binding.registerEtPw.text.toString()
            val username = binding.registerEtName.text.toString()
            val phoneNumber = binding.registerEtPhoneNumber.text.toString()
            val postRequest = PostSignUpRequest(email, password, username, phoneNumber)


            if (isOkayRegister(email,password, username, phoneNumber)) {
                showLoadingDialog(this)
                RegisterService(this).tryPostSignUp(postRequest)
            }

        }

        // 뒤로가기
        binding.registerBtnCancelInToolbar.setOnClickListener {
            finish()
        }
    }

    fun isOkayRegister(email:String,password:String,username:String,phoneNumber:String): Boolean {

        var isOkay = true

        if (email.equals("")){
            binding.registerHelperTextEmail.visibility = VISIBLE
            binding.registerHelperTextEmail.text = "이메일을 입력하세요"
            isOkay = false
            binding.registerEtEmail.setSelection(0)
        }
        else{
            binding.registerHelperTextEmail.visibility = GONE
        }

        if(password.equals("")){
            binding.registerTvPrecautionPassword.visibility = GONE
            binding.registerHelperTextPassword.visibility = VISIBLE
            binding.registerHelperTextPassword.text = "비밀번호는 6~15자 이내로 입력하셔야 합니다."
            isOkay = false
            binding.registerEtPw.setSelection(0)
        }
        else{
            binding.registerHelperTextPassword.visibility = GONE
        }

        if(username.equals("")){
            binding.registerHelperTextName.visibility = VISIBLE
            binding.registerHelperTextName.text = "이름을 정확히 입력하세요."
            isOkay = false
            binding.registerEtName.setSelection(0)
        }
        else{
            binding.registerHelperTextName.visibility = GONE
        }

        if(phoneNumber.equals("")){
            binding.registerHelperTextPhoneNumber.visibility = VISIBLE
            binding.registerHelperTextPhoneNumber.text = "휴대폰 번호를 정확하게 입력하세요."
            isOkay = false
            binding.registerEtPhoneNumber.setSelection(0)
        }
        else{
            binding.registerHelperTextPhoneNumber.visibility = GONE
        }


        binding.registerCheckboxAllAgree.isChecked = isAllAgree()
        if(isAllAgree() == false){
            binding.registerTvPrecautionAgree.visibility = VISIBLE
            isOkay = false
        }
        else{
            binding.registerTvPrecautionAgree.visibility = GONE
        }

        return isOkay

    }

    fun isAllAgree(): Boolean {
        // 모두 동의가 아니라면 false
        if (binding.registerCheckboxAgree1.isChecked == false) return false
        if (binding.registerCheckboxAgree2.isChecked == false) return false
        if (binding.registerCheckboxAgree3.isChecked == false) return false
        if (binding.registerCheckboxAgree4.isChecked == false) return false
        if (binding.registerCheckboxAgree5.isChecked == false) return false

        // 모두 동의면 true
        return true
    }


    fun isEmailValid(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    // 가입 통신 성공
    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()

        val code = response.code

        // 가입 성공하면 메인 화면으로
        if (code == 1000) {
            //Log.d(TAG,"RegisterActivity - onPostSignUpSuccess() called - jwt : ${response.result.jwt}")
            sSharedPreferences.edit().putString(X_ACCESS_TOKEN,response.result.jwt).apply()
            sSharedPreferences.edit().putInt("user_idx", response.result.userIdx).apply()

            //Log.d(TAG,"RegisterActivity - sSharedPreferences - jwt : ${sSharedPreferences.getString(X_ACCESS_TOKEN,"")}")
            startActivity(Intent(this, MainActivity::class.java))
        }
        // 메세지나 코드에 따라 오류 위치 조정
        else if (code in 2015..2017) { // 이메일 오류
            binding.registerHelperTextEmail.visibility = VISIBLE
            binding.registerHelperTextEmail.text = response.message
            binding.registerEtEmail.setSelection(0)

        } else if (code == 2018) { // 중복된 핸드폰 번호
            binding.registerHelperTextPhoneNumber.visibility = VISIBLE
            binding.registerHelperTextPhoneNumber.text = response.message
            binding.registerEtPhoneNumber.setSelection(0)
        } else {
            response.message?.let { showCustomToast(it) }
        }

        showCustomToast("${response.code} : ${response.message}")

    }

    // 가입 통신 실패
    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }
}