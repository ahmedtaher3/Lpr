package com.example.myapplication.ui.main.fragments.history


import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: HistoryRepository
) : BaseViewModel(repository) {



    val historyState = SingleLiveEvent<Status>()
    fun getHistory(type: String, page: Int) {
        performNetworkCall({
            repository.getHistoryApi(type, page)
        }, historyState)
    }


}