package com.aditPrayogo.githubusers.di

import com.aditprayogo.core.domain.usecase.UserUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Aditiya Prayogo.
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun userUseCase() : UserUseCase
}