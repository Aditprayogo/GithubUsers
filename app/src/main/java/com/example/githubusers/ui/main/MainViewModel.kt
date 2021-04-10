package com.example.githubusers.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.data.local.responses.UserSearchResponseItem
import com.example.githubusers.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {


    /**
     * Loading state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    /**
     * Error
     */
    private val _error  = MutableLiveData<String>()

    /**
     * Network Error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean>
        get() = _networkError

    /**
     * Result user from API
     */
    private val _resultUserApi = MutableLiveData<List<UserSearchResponseItem>>()
    val resultUserApi: LiveData<List<UserSearchResponseItem>>
        get() = _resultUserApi

    fun getUserFromApi(query: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            val result = userUseCase.getUserFromApi(query)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserApi.postValue(result.data?.userItems)
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