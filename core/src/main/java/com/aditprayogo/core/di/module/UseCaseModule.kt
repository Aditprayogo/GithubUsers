package com.aditprayogo.core.di.module

import com.aditprayogo.core.data.local.repository.UserRepository
import com.aditprayogo.core.domain.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideUserUseCaseModule(userRepository: UserRepository) : UserUseCase {
        return UserUseCase(userRepository)
    }
}