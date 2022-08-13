package com.example.myapplication.ui.auth.resetPassword

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityResetPasswordBinding
import com.example.myapplication.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity : BaseActivity<ActivityResetPasswordBinding>() {

    lateinit var binding: ActivityResetPasswordBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        viewModel.title.observe(this, Observer {
            binding.title.text = it
        })
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reset_password
    }
}