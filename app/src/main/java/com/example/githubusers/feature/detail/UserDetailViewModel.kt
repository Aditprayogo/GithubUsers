package com.example.githubusers.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.Coroutine
import com.example.githubusers.data.db.entity.UserFavorite
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
     * User detail remote
     */
    private val _resultUserDetail = MutableLiveData<UserDetailResponse>()
    val resultUserDetail : LiveData<UserDetailResponse>
        get() = _resultUserDetail

    /**
     * User Detail from DB
     */
    private val _resultUserDetailFromDb = MutableLiveData<List<UserFavorite>>()
    val resultUserDetailFromDb : LiveData<List<UserFavorite>>
        get() = _resultUserDetailFromDb

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

    /**
     * Local
     */
    fun addUserToFavDB(userFavorite: UserFavorite) {
        Coroutine.main {
            try {
                userUseCase.addUserToFavDB(userFavorite)
                _resultInsertUserToDb.postValue(true)
            }catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    fun deleteUserFromDb(userFavorite: UserFavorite) {
        Coroutine.main {
            try {
                userUseCase.deleteUserFromDb(userFavorite)
                _resultDeleteFromDb.postValue(true)
            }catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    fun getFavUserByUsername(username: String) {
        Coroutine.main {
            val result = userUseCase.getFavUserByUsername(username)
            when(result) {
                is ResultState.Success -> _resultUserDetailFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }
}