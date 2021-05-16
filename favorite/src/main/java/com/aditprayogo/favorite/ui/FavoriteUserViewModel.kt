package com.aditprayogo.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FavoriteUserViewModel(
    userUseCase: UserUseCase
) : ViewModel() {

    val fetchAllUserFavorite = userUseCase.fetchAllUserFavorite().asLiveData()

}