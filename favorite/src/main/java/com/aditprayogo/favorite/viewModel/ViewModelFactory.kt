package com.aditprayogo.favorite.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.favorite.ui.FavoriteUserViewModel
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val userUseCase: UserUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteUserViewModel::class.java) -> {
                FavoriteUserViewModel(userUseCase) as T
            }
            else -> throw Throwable("Unkwnown Viewmodel class: " + modelClass.name)
        }

}