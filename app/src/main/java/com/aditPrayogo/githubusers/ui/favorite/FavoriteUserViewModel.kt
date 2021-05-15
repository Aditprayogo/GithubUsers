package com.aditPrayogo.githubusers.ui.favorite

import androidx.lifecycle.*
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

    init {
        fetchAllUserFavorite()
    }

    fun fetchAllUserFavorite() = userUseCaseImpl.fetchAllUserFavorite().asLiveData()

}