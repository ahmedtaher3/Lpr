package com.example.myapplication.ui.auth.resetPassword

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentPinCodePasswordBinding
import com.example.myapplication.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PinCodeFragment(
    private val code: String,
) : BaseFragment<FragmentPinCodePasswordBinding>() {

    lateinit var binding: FragmentPinCodePasswordBinding
    val viewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.title.postValue("Enter Pin Code")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pinCode.setOnPinEnteredListener {
            if (it.toString() == code) {



            } else {
                binding.pinCode.setText("")
            }

        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_pin_code_password
    }

}