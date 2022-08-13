package com.example.myapplication.ui.auth


import androidx.lifecycle.MutableLiveData
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel(repository) {

    val title = MutableLiveData<String>()

    val loginState = SingleLiveEvent<Status>()
    fun login(email: String, password: String) {
        val parms: MutableMap<String, String> = HashMap()
        parms["email"] = email
        parms["password"] = password
        performNetworkCall({
            repository.getLoginApi(parms)
        }, loginState)
    }

    val getProfileState = SingleLiveEvent<Status>()
    fun getProfile() {
        performNetworkCall({
            repository.getProfileApi()
        }, getProfileState)
    }

    val forgetPasswordState = SingleLiveEvent<Status>()
    fun forgetPassword(email: String) {
        performNetworkCall({
            val parms: MutableMap<String, String> = HashMap()
            parms["email"] = email
            repository.getForgetPasswordApi(parms)
        }, forgetPasswordState)
    }

    val resetPasswordState = SingleLiveEvent<Status>()
    fun resetPassword(code: String, password: String, passwordConfirmation: String) {
        performNetworkCall({
            val parms: MutableMap<String, String> = HashMap()
            parms["code"] = code
            parms["password"] = password
            parms["password_confirmation"] = passwordConfirmation
            repository.getResetPasswordApi(parms)
        }, resetPasswordState)
    }

    val logoutState = SingleLiveEvent<Status>()
    fun logout() {
        performNetworkCall({
            repository.getLogoutApi()
        }, logoutState)
    }

}