package com.example.myapplication.ui.main.fragments.setting.gates


import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeGateViewModel @Inject constructor(
    private val repository: ChangeGateRepository
) : BaseViewModel(repository) {


    val sitesState = SingleLiveEvent<Status>()
    fun getSites() {
        performNetworkCall({
            repository.getSitesApi()
        }, sitesState)
    }

    val gatesState = SingleLiveEvent<Status>()
    fun getGates(id: Int) {
        performNetworkCall({
            repository.getGatesApi(id)
        }, gatesState)
    }

}