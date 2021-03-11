package com.example.githubusers.di.module

import com.example.githubusers.domain.UserRepository
import com.example.githubusers.domain.UserUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class UseCaseModule {

    @Provides
    fun provideUserUseCaseModule(userRepository: UserRepository) : UserUseCase {
        return UserUseCase(userRepository)
    }
}