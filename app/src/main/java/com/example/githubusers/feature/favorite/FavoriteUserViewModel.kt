package com.example.githubusers.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusers.core.state.LoaderState
import com.example.githubusers.core.state.ResultState
import com.example.githubusers.core.util.Coroutine
import com.example.githubusers.data.db.entity.UserFavorite
import com.example.githubusers.domain.UserUseCase
import javax.inject.Inject

class FavoriteUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    /**
     * error
     */
    private val _error = MutableLiveData<String>()
    val error : LiveData<String>
        get() = _error

    /**
     * Result delete user from db
     */
    private val _resultDeleteUserFromDb = MutableLiveData<Boolean>()
    val resultDeleteUserFromDb : LiveData<Boolean>
        get() = _resultDeleteUserFromDb

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
        Coroutine.main {
            val result = userUseCase.fetchAllUserFavorite()
            when(result) {
                is ResultState.Success -> _resultUserFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

    fun deleteUserFromDb(userFavorite: UserFavorite) {
        Coroutine.main {
            try {
                userUseCase.deleteUserFromDb(userFavorite)
                _resultDeleteUserFromDb.postValue(true)
            }catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }


}