package com.example.myapplication.ui.main.newVisitor


import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActionViewModel @Inject constructor(
    private val repository: ActionRepository
) : BaseViewModel(repository) {


    val scanCarState = SingleLiveEvent<Status>()
    fun scanCar(base64Object: JsonObject) {
        performNetworkCall({
            repository.getScanCarApi(base64Object)
        }, scanCarState)
    }


    val sendActionState = SingleLiveEvent<Status>()
    fun sendAction(car_request_id: String , status_action: String , note: String) {
        val parms: MutableMap<String, String> = HashMap()
        parms["car_request_id"] = car_request_id
        parms["status_action"] = status_action
        parms["note"] = note
        performNetworkCall({
            repository.getSendActionApi(parms)
        }, sendActionState)
    }

}