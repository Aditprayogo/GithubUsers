package com.example.githubusers.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.loader.app.LoaderManager
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.Coroutine
import com.example.githubusers.data.entity.UserDetailResponse
import com.example.githubusers.domain.UserUseCase
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    /**
     * error
     */
    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    /**
     * User detail
     */
    private val _resultUserDetail = MutableLiveData<UserDetailResponse>()
    val resultUserDetail : LiveData<UserDetailResponse>
        get() = _resultUserDetail

    /**
     * Remote
     */
    fun getUserDetailFromApi(username: String) {
        _state.value = LoaderState.ShowLoading
        Coroutine.main {
            val result = userUseCase.getUserDetailFromApi(username)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserDetail.postValue(result.data)
                }
                is ResultState.Error -> {
                    _error.postValue(result.error)
                }
                is ResultState.NetworkError -> {
                    _networkError.postValue(true)
                }
            }
        }
    }
}