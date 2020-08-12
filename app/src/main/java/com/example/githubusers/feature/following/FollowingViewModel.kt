package com.example.githubusers.feature.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.Coroutine
import com.example.githubusers.data.entity.UserFollowingResponseItem
import com.example.githubusers.domain.UserUseCase
import javax.inject.Inject

class FollowingViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState>
        get() = _state

    private val _resultUserFollowing = MutableLiveData<List<UserFollowingResponseItem>>()
    val resultUserFollowing : LiveData<List<UserFollowingResponseItem>>
        get() = _resultUserFollowing

    fun getUserFollowing(username: String) {
        _state.value = LoaderState.ShowLoading
        Coroutine.main {
            val result = userUseCase.getUserFollowing(username)
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> {
                    _resultUserFollowing.postValue(result.data)
                }
            }
        }
    }

}