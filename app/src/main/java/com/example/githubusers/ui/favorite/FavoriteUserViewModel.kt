package com.example.githubusers.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.data.local.db.entity.UserFavorite
import com.example.githubusers.domain.UserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * error
     */
    private val _error = MutableLiveData<String>()

    /**
     * Result from db
     */
    private val _resultUserFromDb = MutableLiveData<List<UserFavorite>>()
    val resultUserFromDb : LiveData<List<UserFavorite>>
        get() = _resultUserFromDb

    init {
        fetchAllUserFavorite()
    }

    fun fetchAllUserFavorite() {
        viewModelScope.launch {
            when (val result = userUseCase.fetchAllUserFavorite()) {
                is ResultState.Success -> _resultUserFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}