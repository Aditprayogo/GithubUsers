package com.aditPrayogo.githubusers.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.core.utils.state.ResultState
import com.aditprayogo.core.data.local.db.entity.UserFavoriteEntity
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteUserViewModel @Inject constructor(
    private val userUseCaseImpl: UserUseCaseImpl
) : ViewModel() {

    /**
     * error
     */
    private val _error = MutableLiveData<String>()

    /**
     * Result from db
     */
    private val _resultUserFromDb = MutableLiveData<List<UserFavoriteEntity>>()
    val resultUserFromDbEntity : LiveData<List<UserFavoriteEntity>>
        get() = _resultUserFromDb

    init {
        fetchAllUserFavorite()
    }

    fun fetchAllUserFavorite() {
        viewModelScope.launch {
            when (val result = userUseCaseImpl.fetchAllUserFavorite()) {
                is ResultState.Success -> _resultUserFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}