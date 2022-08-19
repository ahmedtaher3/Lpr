package com.example.myapplication.ui.main.fragments.setting


import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository
) : BaseViewModel(repository) {


    val logoutState = SingleLiveEvent<Status>()
    fun logout() {
        performNetworkCall({
            repository.getLogoutApi()
        }, logoutState)
    }


}