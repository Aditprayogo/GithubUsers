package com.aditPrayogo.githubusers.ui.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditPrayogo.githubusers.utils.state.LoaderState
import com.aditPrayogo.githubusers.utils.state.ResultState
import com.aditPrayogo.githubusers.data.local.responses.UserFollowingResponseItem
import com.aditPrayogo.githubusers.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
        viewModelScope.launch {
            val result = userUseCase.getUserFollowing(username)
            _state.value = LoaderState.HideLoading
            if (result is ResultState.Success) _resultUserFollowing.postValue(result.data)
        }
    }

}