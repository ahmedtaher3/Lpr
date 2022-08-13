package com.example.myapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySplashBinding
import com.example.myapplication.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!


        Handler(Looper.getMainLooper()).postDelayed({
            if (dataManager.isLogin) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()

            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()


            }
        }, 3000)


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }
}