package com.example.myapplication.ui.auth.resetPassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.FragmentForgetPasswordBinding
import com.example.myapplication.ui.auth.AuthViewModel
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.util.CommonUtilities.isEmailValid
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {

    lateinit var binding: FragmentForgetPasswordBinding
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.title.postValue("Forget Password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        binding.forget.setOnClickListener {
            if (!binding.email.text.toString().isEmailValid()) {
                baseActivity.showWarningSnackbar("Email not valid")
                return@setOnClickListener
            }

            viewModel.forgetPassword(binding.email.text.toString())
        }

        setState()

    }

    private fun setState() {
        observe(viewModel.forgetPasswordState)
        {

            when (it) {
                is Status.Loading -> {
                    baseActivity.showDialogLoading()
                }
                is Status.Error -> {
                    baseActivity.hideDialogLoading()
                    baseActivity.showWarningSnackbar(it.message!!)
                }
                is Status.Success<*> -> {
                    val response = it.data as BaseResponse<BaseResponse.Data>


                    baseActivity.hideDialogLoading()
                    baseActivity.showSuccessSnackbar(response.message)
                    replaceFragment(PinCodeFragment() , "ResetPasswordFragment")

                }
                is Status.Unauthorized ->{
                    val intent = Intent(baseActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    baseActivity.startActivity(intent)
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_forget_password
    }
    fun replaceFragment(fragment: Fragment?, tag: String?) {
        baseActivity.supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.container,
                fragment!!
            )
            .addToBackStack(tag)
            .commit()
    }
}