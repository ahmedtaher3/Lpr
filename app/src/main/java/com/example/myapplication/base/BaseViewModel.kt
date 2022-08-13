package com.example.myapplication.base

import com.example.myapplication.util.SingleLiveEvent
import com.example.myapplication.util.Status
import com.example.myapplication.util.extensions.toObjectFromJson
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

private const val TAG = "BaseViewModel"

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {
    fun <T> performNetworkCall(
        apiCall: suspend () -> Response<BaseResponse<T>>,
        status: SingleLiveEvent<Status> = SingleLiveEvent<Status>(),
        doOnSuccess: (response: BaseResponse<T>) -> Unit = {}
    ) {
        if (repository.isNetworkConnected())
            viewModelScope.launch {
                withContext(Dispatchers.Default) {


                    try {
                        status.postValue(Status.Loading)
                        val response = apiCall.invoke()
                        status.postValue(handleResponse(response))

                        try {
                            doOnSuccess(response.body() as BaseResponse<T>)

                        } catch (e: Exception) {

                        }

                    } catch (e: Exception) {
                        Log.d(TAG, "performNetworkCall: ${e.message}")
                        status.postValue(
                            Status.Error(
                                message = repository.getString(R.string.some_thing_went_wrong_error_msg)
                            )
                        )
                    }
                }
            }
        else {
            status.postValue(Status.Error(message = repository.getString(R.string.check_internet_connection)))

        }
    }

    fun doInBackground(error: (e: Exception) -> Unit = {}, block: suspend () -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    block.invoke()
                } catch (e: Exception) {
                    error.invoke(e)
                }
            }
        }
    }




    fun <T> handleResponse(response: Response<BaseResponse<T>>): Status {
        if (response.code() in 200..300) {

            if (response.body()?.isSuccessResponse!!) {
                return Status.Success(response.body())
            } else {
                return try {
                    Status.Error(response.code(), response.body()?.messageResponse!!)
                } catch (e: Exception) {
                    Status.Error(response.code(), response.message())
                }
            }
        }
        else  if (response.code() == 426) {


            return try {
                val error = response.errorBody()?.string()
                    .toObjectFromJson<BaseResponse<T>>(BaseResponse::class.java)
                Status.OldVersion(
                    message = if (error.messageResponse.isNullOrEmpty()) repository.getString(
                        R.string.some_thing_went_wrong_error_msg
                    ) else error.messageResponse
                )

            } catch (e: Exception) {
                Status.OldVersion(response.message())
            }

        }

        else  if (response.code() == 401) {

            return  Status.Unauthorized
        }
        else {


            return try {
                val error = response.errorBody()?.string()
                    .toObjectFromJson<BaseResponse<T>>(BaseResponse::class.java)
                Status.Error(
                    response.code(),
                    message = if (error.messageResponse.isNullOrEmpty()) repository.getString(
                        R.string.some_thing_went_wrong_error_msg
                    ) else error.messageResponse
                )


            } catch (e: Exception) {
                Status.Error(response.code(), response.message())
            }

        }
    }

    fun <T> checkResponse(response: Response<BaseResponse<T>>): Boolean {
        return when (handleResponse(response)) {
            is Status.Success<*> -> {
                true
            }
            else -> {
                false
            }
        }
    }


}