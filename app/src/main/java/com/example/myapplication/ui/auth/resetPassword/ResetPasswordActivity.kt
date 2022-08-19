package com.example.myapplication.ui.auth.resetPassword

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

        binding.back.setOnClickListener {
            onBackPressed()
        }

        replaceFragment(ForgetPasswordFragment() , "ForgetPasswordFragment")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_reset_password
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_left
            )
            .add(
                R.id.container,
                fragment
            )
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}