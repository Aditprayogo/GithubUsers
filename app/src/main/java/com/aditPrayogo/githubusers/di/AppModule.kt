package com.aditPrayogo.githubusers.di

import com.aditprayogo.core.domain.repository.UserRepository
import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideUserUseCase(
        userRepository: UserRepository
    ) : UserUseCase = UserUseCaseImpl(userRepository)
}