package com.example.coupangeats_clone.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.coupangeats_clone.R
import com.example.coupangeats_clone.config.ApplicationClass
import com.example.coupangeats_clone.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.example.coupangeats_clone.config.ApplicationClass.Companion.sSharedPreferences
import com.example.coupangeats_clone.databinding.ActivityMainBinding
import com.example.coupangeats_clone.src.login.NeedLoginFragmentDialog
import com.example.coupangeats_clone.src.main.favorite.FavoriteActivity
import com.example.coupangeats_clone.src.main.favorite.FavoriteFragment
import com.example.coupangeats_clone.src.main.home.HomeFragment
import com.example.coupangeats_clone.src.main.myEats.MyEatsFragment
import com.example.coupangeats_clone.src.main.orderList.OrderListFragment
import com.example.coupangeats_clone.src.main.search.SearchFragment
import com.example.template_practice.config.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when(item.itemId){
                        R.id.menu_main_btm_nav_home->{
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm,HomeFragment())
                                    .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_search->{
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, SearchFragment())
                                .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_favorite->{
//                            supportFragmentManager.beginTransaction()
//                                .replace(R.id.main_frm, FavoriteFragment())
//                                .commitAllowingStateLoss()
//                            return@OnNavigationItemSelectedListener true
                            startActivity(Intent(this,FavoriteActivity::class.java))
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_orderList->{
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, OrderListFragment())
                                    .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.menu_main_btm_nav_myEats->{
                            // 로그인 안되었다면 로그인 필요하다는 dialog 띄우기
//                            Log.d(TAG,"MainActivity - bottom_navigation MyEats - X_ACCESS_TOKEN : ${sSharedPreferences.getString(
//                                X_ACCESS_TOKEN,"")}")
                            if (sSharedPreferences.getString(X_ACCESS_TOKEN,"") == "") {
                                NeedLoginFragmentDialog().show(supportFragmentManager, "NeedLogin")
                            }
                            else {   // 로그인이 되었다면 My 이츠 화면 보여주기
                            supportFragmentManager.beginTransaction()
                                    .replace(R.id.main_frm, MyEatsFragment())
                                    .commitAllowingStateLoss()
                                return@OnNavigationItemSelectedListener true
                            }
                        }
                    }
                    false
                }
        )
    }


    override fun onDestroy() {
        super.onDestroy()
       sSharedPreferences.edit().clear().commit()
        //Log.d(TAG,"MainActivity - onDestroy() called${sSharedPreferences.getString(X_ACCESS_TOKEN,"없음")}")
    }
}
