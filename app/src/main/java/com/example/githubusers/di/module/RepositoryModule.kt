package com.example.githubusers.di.module

import com.example.githubusers.data.local.db.dao.UserFavoriteDao
import com.example.githubusers.data.remote.NetworkService
import com.example.githubusers.data.local.repository.UserRepository
import com.example.githubusers.data.local.repository.UserRepositoryImpl
import com.example.githubusers.di.DataScope
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class, RoomModule::class])
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        @DataScope service: NetworkService,
        userFavoriteDao: UserFavoriteDao
    ) : UserRepository {
        return UserRepositoryImpl(service, userFavoriteDao)
    }
}