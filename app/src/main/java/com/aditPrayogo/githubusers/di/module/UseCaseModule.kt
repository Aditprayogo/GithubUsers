package com.aditPrayogo.githubusers.di.module

import com.aditPrayogo.githubusers.data.local.repository.UserRepository
import com.aditPrayogo.githubusers.domain.UserUseCase
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