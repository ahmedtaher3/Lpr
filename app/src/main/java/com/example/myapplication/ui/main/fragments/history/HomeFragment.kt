package com.example.myapplication.ui.main.fragments.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.HistoryData
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.ui.main.MainActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.observe
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var binding: FragmentHomeBinding
    val viewModel : HistoryViewModel by viewModels()
    lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HistoryAdapter(ArrayList())

    }



    private fun setState() {
        observe(viewModel.historyState)
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
                    baseActivity.hideDialogLoading()
                    val response = it.data as BaseResponse<ArrayList<HistoryData>>
                    response.data!!.reverse();
                    adapter.setMyData(response.data!!)

                }
                is Status.Unauthorized ->{
                    val intent = Intent(baseActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    baseActivity.startActivity(intent)
                }
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding!!

        binding.history.adapter = adapter
        viewModel.getHistory()
        setState()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}