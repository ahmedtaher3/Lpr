package com.example.myapplication.ui.main.fragments.setting

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.data.shared.DataManager
import com.example.myapplication.databinding.FragmentSettingBinding
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.ui.main.fragments.setting.gates.ChangeGateActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    lateinit var binding: FragmentSettingBinding
    val viewModel: SettingViewModel by viewModels()

    @Inject
    lateinit var dataManager: DataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!

        binding.name.text = dataManager.user?.user?.fullName
        binding.mail.text = dataManager.user?.user?.email
        try {
            binding.site.text = dataManager.site?.name
        } catch (e: Exception) {

        }
        binding.gate.text = dataManager.gate.name

        binding.changeGate.setOnClickListener {

            startActivity(Intent(baseActivity, ChangeGateActivity::class.java))

        }

        binding.logOut.setOnClickListener {

            viewModel.logout()

        }
        setState()

    }

    private fun setState() {

        observe(viewModel.logoutState)
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
                    dataManager.saveIsLogin(false)
                    showToast(response.message)
                    val intent = Intent(baseActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    baseActivity.startActivity(intent)

                }
                is Status.Unauthorized -> {
                    val intent = Intent(baseActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    baseActivity.startActivity(intent)
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_setting
    }

}