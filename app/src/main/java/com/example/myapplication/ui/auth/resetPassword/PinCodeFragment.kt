package com.example.myapplication.ui.auth.resetPassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentPinCodePasswordBinding
import com.example.myapplication.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PinCodeFragment : BaseFragment<FragmentPinCodePasswordBinding>() {

    lateinit var binding: FragmentPinCodePasswordBinding
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.title.postValue("Enter Pin Code")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!
        binding.pinCode.setOnPinEnteredListener {

            replaceFragment(ResetPasswordFragment(it.toString()) , "ResetPasswordFragment")


        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pin_code_password
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