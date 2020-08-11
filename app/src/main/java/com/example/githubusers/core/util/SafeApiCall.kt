package com.example.githubusers.core.util

import com.bumptech.glide.load.HttpException
import com.example.githubusers.core.state.ResultState

import java.io.IOException
import java.net.ConnectException


suspend fun <T: Any> safeApiCall(apiCall: suspend () -> ResultState<T>) : ResultState<T> {
    return try {
        apiCall.invoke()
     }catch (throwable: Throwable) {
        when(throwable) {
            is IOException -> ResultState.NetworkError
            is HttpException -> {
                val code = throwable.statusCode
                val errorResponse = throwable.message
                return ResultState.Error(errorResponse, code)
            }
            is ConnectException -> ResultState.NetworkError
            else -> ResultState.Error(null, 500)
        }
    }
}