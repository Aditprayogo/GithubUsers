package com.aditPrayogo.githubusers.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.aditprayogo.core.domain.model.UserDetail
import com.aditprayogo.core.domain.model.UserFavorite
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import com.aditprayogo.core.utils.state.LoaderState
import com.aditprayogo.core.utils.state.ResultState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class UserDetailViewModel @ViewModelInject constructor(
    private val userUseCaseImpl: UserUseCaseImpl
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

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()

    /**
     * User detail remote
     */
    private val _resultUserDetail = MutableLiveData<UserDetail>()
    val resultUserDetail : LiveData<UserDetail>
        get() = _resultUserDetail

    /**
     * Insert to DB
     */
    private val _resultInsertUserToDb = MutableLiveData<Boolean>()
    val resultInsertUserDb : LiveData<Boolean>
        get() = _resultInsertUserToDb

    /**
     * Delete from db
     */
    private val _resultDeleteFromDb = MutableLiveData<Boolean>()
    val resultDeleteFromDb : LiveData<Boolean>
        get() = _resultDeleteFromDb

    /**
     * Remote
     */
    fun getUserDetailFromApi(username: String) {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            userUseCaseImpl.getUserDetailFromApi(username).collect {
                _state.value = LoaderState.HideLoading
                when(it) {
                    is ResultState.Success -> _resultUserDetail.postValue(it.data)
                    is ResultState.Error -> _error.postValue(it.error)
                    is ResultState.NetworkError -> _networkError.postValue(true)
                }
            }

        }
    }

    /**
     * Local
     */
    fun addUserToFavDB(userFavoriteEntity: UserFavorite) {
        viewModelScope.launch {
            try {
                userUseCaseImpl.addUserToFavDB(userFavoriteEntity)
                _resultInsertUserToDb.postValue(true)
            }catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    fun deleteUserFromDb(userFavoriteEntity: UserFavorite) {
        viewModelScope.launch {
            try {
                userUseCaseImpl.deleteUserFromDb(userFavoriteEntity)
                _resultDeleteFromDb.postValue(true)
            }catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    fun getFavUserByUsername(username: String) = userUseCaseImpl.getFavUserByUsername(username).asLiveData()
}