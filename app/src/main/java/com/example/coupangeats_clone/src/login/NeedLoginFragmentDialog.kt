package com.example.coupangeats_clone.src.login

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.webkit.WebHistoryItem
import android.widget.RelativeLayout
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.FragmentDialogNeedLoginBinding
import com.example.coupangeats_clone.src.register.RegisterActivity


class NeedLoginFragmentDialog:DialogFragment() {

    private lateinit var binding: FragmentDialogNeedLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val window = dialog?.window

        window?.setGravity(Gravity.BOTTOM or Gravity.LEFT)

        binding = FragmentDialogNeedLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 쿠팡이츠 로그인
        binding.frDialogNeedLoginBtnLoginWithCoupangEats.setOnClickListener {
            startActivity(Intent(context,CoupangIdLoginActivity::class.java))
        }

        // 회원가입
        binding.frDialogNeedLoginLl.setOnClickListener {
            startActivity(Intent(context,RegisterActivity::class.java))
        }
        // 회원가입
        binding.frDialogNeedLoginBtnRegister.setOnClickListener {
            startActivity(Intent(context,RegisterActivity::class.java))
        }


    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(activity!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )



        return dialog
    }
}