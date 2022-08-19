package com.example.myapplication.ui.main.newVisitor


import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.getImageFilePart
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ActionViewModel @Inject constructor(
    private val repository: ActionRepository
) : BaseViewModel(repository) {


    val scanCarState = SingleLiveEvent<Status>()
    fun scanCar(file: File) {
        performNetworkCall({
            repository.getScanCarApi(file.getImageFilePart("image"))
        }, scanCarState)
    }

    val confirmCarState = SingleLiveEvent<Status>()
    fun confirmCar(plate_en: String , plate_ar: String) {
        performNetworkCall({
            val parms: MutableMap<String, String> = HashMap()
            parms["plate_en"] = plate_en
            parms["plate_ar"] = plate_ar
            repository.getConfirmCarApi(parms)
        }, confirmCarState)
    }


    val scanQrState = SingleLiveEvent<Status>()
    fun scanQr(text: String) {
        performNetworkCall({
            val parms: MutableMap<String, String> = HashMap()
            parms["qr_code"] = text
            repository.getScanQrApi(parms)
        }, scanQrState)
    }


    val sendActionState = SingleLiveEvent<Status>()
    fun sendAction(car_request_id: String, status_action: String, note: String) {
        val parms: MutableMap<String, String> = HashMap()
        parms["history_id"] = car_request_id
        parms["status_action"] = status_action
        parms["note"] = note
        performNetworkCall({
            repository.getSendActionApi(parms)
        }, sendActionState)
    }

}