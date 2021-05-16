package com.aditPrayogo.githubusers.di

import com.aditprayogo.core.domain.usecase.UserUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Aditiya Prayogo.
 */
@EntryPoint
@InstallIn(ViewModelComponent::class)
interface FavoriteModuleDependencies {
    fun userUseCase() : UserUseCase
}