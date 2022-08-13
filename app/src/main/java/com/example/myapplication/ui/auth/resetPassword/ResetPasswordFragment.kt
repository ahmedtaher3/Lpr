package com.example.myapplication.ui.auth.resetPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.FragmentResetPasswordBinding
import com.example.myapplication.ui.auth.AuthViewModel
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ResetPasswordFragment(private val code: String) :
    BaseFragment<FragmentResetPasswordBinding>() {

    lateinit var binding: FragmentResetPasswordBinding
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.title.postValue("Reset Password")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.reset.setOnClickListener {
            if (!binding.password.text.toString().isNullOrEmpty()) {
                baseActivity.showWarningSnackbar("password not valid")
                return@setOnClickListener
            }

            if (binding.password.text.toString() == binding.confirmPassword.text.toString()) {
                baseActivity.showWarningSnackbar("password not valid")
                return@setOnClickListener
            }

            viewModel.resetPassword(
                code,
                binding.password.text.toString(),
                binding.confirmPassword.text.toString()
            )
        }

        setState()

    }

    private fun setState() {
        observe(viewModel.resetPasswordState)
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
                    baseActivity.showToast(response.data?.message!!)
                    baseActivity.finish()


                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_reset_password
    }

}