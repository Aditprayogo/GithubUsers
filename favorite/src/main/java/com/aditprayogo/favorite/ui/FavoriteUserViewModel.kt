package com.aditprayogo.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditprayogo.core.domain.usecase.UserUseCase

class FavoriteUserViewModel(
    userUseCase: UserUseCase
) : ViewModel() {
    val fetchAllUserFavorite = userUseCase.fetchAllUserFavorite().asLiveData()
}