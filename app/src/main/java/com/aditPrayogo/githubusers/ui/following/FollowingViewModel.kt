package com.aditPrayogo.githubusers.ui.following

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.data.local.responses.UserFollowingResponseItem
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import com.aditprayogo.core.utils.state.LoaderState
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FollowingViewModel @ViewModelInject constructor(
    private val userUseCaseImpl: UserUseCaseImpl
) : ViewModel() {

    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    private val _resultUserFollowing = MutableLiveData<List<UserFollowingResponseItem>>()
    val resultUserFollowing : LiveData<List<UserFollowingResponseItem>>
        get() = _resultUserFollowing

    fun getUserFollowing(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCaseImpl.getUserFollowing(username).collect {
                _state.value = LoaderState.HideLoading
                if (it is ResultState.Success) _resultUserFollowing.postValue(it.data)
            }
        }
    }

}