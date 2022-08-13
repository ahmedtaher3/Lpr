package com.example.myapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    lateinit var binding: ActivityLoginBinding
    val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewDataBinding!!

        binding.login.setOnClickListener {
            viewModel.login(binding.email.text.toString(), binding.password.text.toString())
        }

        binding.forgetPass.setOnClickListener {


        }


        setState()

    }

    private fun setState() {

        observe(viewModel.loginState)
        {

            when (it) {
                is Status.Loading -> {
                    showDialogLoading()
                }
                is Status.Error -> {
                    hideDialogLoading()
                    showWarningSnackbar(it.message!!)
                }
                is Status.Success<*> -> {
                    hideDialogLoading()
                    val response = it.data as BaseResponse<BaseResponse.Data>
                    dataManager.saveToken(response.accessToken)
                    dataManager.saveIsLogin(true)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }
}