package com.example.githubusers.feature.favorite

import androidx.lifecycle.ViewModel
import com.example.githubusers.domain.UserUseCase
import javax.inject.Inject

class FavoriteUserViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
}