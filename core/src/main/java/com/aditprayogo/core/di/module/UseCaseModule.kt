package com.aditprayogo.core.di.module

import com.aditprayogo.core.domain.usecase.UserUseCase
import com.aditprayogo.core.domain.usecase.UserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideUserUseCaseModule(userUseCaseImpl: UserUseCaseImpl) : UserUseCase
}