package com.aditPrayogo.githubusers.di

import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideUserUseCase(userUseCaseImpl: UserUseCaseImpl) : UserUseCase

}