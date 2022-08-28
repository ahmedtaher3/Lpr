package com.example.myapplication.ui.main.fragments.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.base.BaseResponse
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.HistoryData
import com.example.myapplication.ui.auth.LoginActivity
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.hide
import com.example.myapplication.util.extensions.observe
import com.example.myapplication.util.extensions.visible
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: HistoryViewModel by viewModels()
    lateinit var adapter: HistoryAdapter


    var page = 1
    var hasData = true
    var type = "all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HistoryAdapter(baseActivity, ArrayList())

    }


    private fun setState() {
        observe(viewModel.historyState)
        {

            binding.allProgress.isVisible = it == Status.Loading


            when (it) {


                is Status.Loading -> {
                }
                is Status.Error -> {
                    baseActivity.showWarningSnackbar(it.message!!)
                }
                is Status.Success<*> -> {
                    val response = it.data as BaseResponse<ArrayList<HistoryData>>
                    if (response.data.isNullOrEmpty()) {
                        hasData = false
                        if (adapter.getMyData().isEmpty()) {
                            binding.empty.visible()
                        }
                    }
                    else
                    {
                        binding.empty.hide()
                    }
                    adapter.addData(response.data!!)

                }
                is Status.Unauthorized -> {
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
        viewModel.getHistory(type, page)


        binding.history.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {


                    if (hasData) {
                        page++
                        viewModel.getHistory(type, page)
                    }

                }
            }
        })


        binding.taps.addTab(binding.taps.newTab().setText("All"));
        binding.taps.addTab(binding.taps.newTab().setText("Checkin"));
        binding.taps.addTab(binding.taps.newTab().setText("Checkout"));
        binding.taps.addTab(binding.taps.newTab().setText("Refused"));

        binding.taps.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {


                when (tab.position) {
                    0 -> {
                        type = ("all")
                        page = 1
                        hasData = true
                        adapter.setMyData(ArrayList())
                        viewModel.getHistory(type, page)
                    }
                    1 -> {
                        type = ("checkin")
                        page = 1
                        hasData = true
                        adapter.setMyData(ArrayList())
                        viewModel.getHistory(type, page)
                    }
                    2 -> {
                        type = "checkout"
                        page = 1
                        hasData = true
                        adapter.setMyData(ArrayList())
                        viewModel.getHistory(type, page)
                    }
                    3 -> {
                        type = ("refused")
                        page = 1
                        hasData = true
                        adapter.setMyData(ArrayList())
                        viewModel.getHistory(type, page)
                    }
                }


            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        setState()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

}