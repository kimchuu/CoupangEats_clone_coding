package com.example.coupangeats_clone.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.databinding.ActivitySplashBinding
import com.example.coupangeats_clone.src.main.MainActivity
import com.example.template_practice.config.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var handler = Handler(Looper.getMainLooper())
        supportFragmentManager.beginTransaction().replace(R.id.splash_fl_container, SplashLogoFragment()).commitAllowingStateLoss()


        handler.postDelayed({
            supportFragmentManager.beginTransaction().replace(R.id.splash_fl_container, SplashHanFragment()).commitAllowingStateLoss()
        }, 1000)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1500)
    }
}